import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import {MessageService} from 'primeng/api';
import {ApplicationInitService} from '../../../../services/application-init.service';
import { SexoDTO } from 'src/app/models/generales/sexo-dto';
import {NgForm, NG_VALUE_ACCESSOR} from '@angular/forms';
import { FamiliarBeneficiarioDto } from 'src/app/models/familiar_beneficiario/familiar-beneficiario-dto';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { RelacionFamiliarDTO } from 'src/app/models/generales/relacion-familiar-dto';
import * as moment_ from 'moment';

@Component({
  selector: 'app-familiar-alta',
  templateUrl: './familiar-alta.component.html',
  styleUrls: ['./familiar-alta.component.css'],
  providers: [MessageService]
})
export class FamiliarAltaComponent implements OnInit {

  public nuevoMiembroArquitecturaDTO: FamiliarBeneficiarioDto;
  sexos: SexoDTO[] = [];
  sexoSeleccionado: SexoDTO;
  es: any;
  display = false;
  @BlockUI() blockUI: NgBlockUI;
  catParentesco: RelacionFamiliarDTO[] = [];
  parentescoSeleccionado: RelacionFamiliarDTO;
  fhNacimiento: Date;

  constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
    private applicationInitService: ApplicationInitService) {

      this.breadcrumbService.setItems([
      { label: 'Registro', routerLink: ['/familiarBeneficiario/registro'] }
      ]);

    }

  ngOnInit(): void {
    this.nuevoMiembroArquitecturaDTO = new FamiliarBeneficiarioDto();
    this.blockUI.start();
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre',
          'noviembre', 'diciembre'],
      monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
      today: 'Hoy',
      clear: 'Borrar',
      dateFormat: 'dd/mm/yy'
  };

    
  this.obtenerCatSexo();

  }

  obtenerCatSexo(){
    this.applicationInitService.getCatalogoSexo().subscribe(data3 => {
      this.sexos = data3;
      this.sexoSeleccionado = this.sexos[0];
      this.obtenerCatRelacionFamiliar();
    }, error => {
        this.blockUI.stop();
        this.sexos = [];
        this.messageService.add({severity: 'error', summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
    
    });
  }

  obtenerCatRelacionFamiliar(){
    this.applicationInitService.getCatalogoRelacionesFamiliares().subscribe(dataRelacion => {
      this.blockUI.stop();
      this.catParentesco = dataRelacion;
      this.parentescoSeleccionado = this.catParentesco[0];
    },error => {
      this.blockUI.stop();
      this.catParentesco = [];
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
  
    });
  }

  registrarUsuario(formRegistro: NgForm) {
    this.blockUI.start('Guardando...');
    this.nuevoMiembroArquitecturaDTO.relacionFamiliar = this.parentescoSeleccionado;
    this.nuevoMiembroArquitecturaDTO.sexo = this.sexoSeleccionado;
  
   var momentSTR = moment_(this.fhNacimiento, "YYYY/MM/DD").format("YYYY-MM-DD");
   this.nuevoMiembroArquitecturaDTO.fechaNaciomiento = momentSTR;
    this.applicationInitService.nuevoFamiliarBeneficiario(this.nuevoMiembroArquitecturaDTO).subscribe (isSuccess => {
      console.log('Se guardo?  '+ isSuccess);
      this.blockUI.stop();
      this.resetComponents();
      formRegistro.reset();
      this.messageService.add({severity: 'success', summary: 'Operación exitosa', detail: 'El familiar se creó satisfactoriamente.'});
    }, error =>{
      this.blockUI.stop();
      this.catParentesco = [];
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error al guardar los datos del familiar.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
    });

  }

  resetComponents(){
    this.nuevoMiembroArquitecturaDTO = new FamiliarBeneficiarioDto();
    this.sexoSeleccionado = this.sexos[0];
    this.parentescoSeleccionado = this.catParentesco[0];
    this.fhNacimiento = null;
  }

  closeDialog() {
    this.display = false;
  }

}
