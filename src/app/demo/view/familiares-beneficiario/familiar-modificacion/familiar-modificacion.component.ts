import { Component, OnInit } from '@angular/core';
import { FamiliarBeneficiarioDto } from 'src/app/models/familiar_beneficiario/familiar-beneficiario-dto';
import { Router } from '@angular/router';
import { SexoDTO } from 'src/app/models/generales/sexo-dto';
import { RelacionFamiliarDTO } from 'src/app/models/generales/relacion-familiar-dto';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ApplicationInitService } from 'src/app/services/application-init.service';
import { BreadcrumbService } from 'src/app/breadcrumb.service';
import { NgForm } from '@angular/forms';
import * as moment_ from 'moment';
import {MessageService} from 'primeng/api';
import { HostListener } from '@angular/core';

@Component({
  selector: 'app-familiar-modificacion',
  templateUrl: './familiar-modificacion.component.html',
  styleUrls: ['./familiar-modificacion.component.css'],
  providers: [MessageService]
})
export class FamiliarModificacionComponent implements OnInit {


  public familiarBeneficiarioDTO: FamiliarBeneficiarioDto; 
  sexos: SexoDTO[] = [];
  sexoSeleccionado: SexoDTO;
  es: any;
  display = false;
  catParentesco: RelacionFamiliarDTO[] = [];
  parentescoSeleccionado: RelacionFamiliarDTO;
  fhNacimiento: Date;
  @BlockUI() blockUI: NgBlockUI;

  constructor(private router: Router,
    private applicationInitService: ApplicationInitService,
    private breadcrumbService: BreadcrumbService,
    private messageService: MessageService) {

      this.breadcrumbService.setItems([
        { label: 'Modificación', routerLink: ['/familiarBeneficiario/todos'] }
      ]);

      const navigation = this.router.getCurrentNavigation();
      const resultExtra = navigation.extras.state;
      if(resultExtra != undefined){
        this.familiarBeneficiarioDTO = resultExtra.famBeneficiarioDTO;
        this.fhNacimiento = moment_(this.familiarBeneficiarioDTO.fechaNaciomiento,'YYYY-MM-DD').toDate();
        console.log(this.familiarBeneficiarioDTO.apMaterno);
      }else{
        this.router.navigate( ['familiarBeneficiario/todos']);
      }

     }

  ngOnInit(): void {

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

      

      this.sexoSeleccionado = this.sexos.find(it => it.idSexo == this.familiarBeneficiarioDTO.sexo.idSexo);
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
      this.parentescoSeleccionado = this.familiarBeneficiarioDTO.relacionFamiliar;
    },error => {
      this.blockUI.stop();
      this.catParentesco = [];
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
  
    });
  }

  actualizaDatos(formRegistro: NgForm) {
    this.blockUI.start('Guardando...');
    this.familiarBeneficiarioDTO.relacionFamiliar = this.parentescoSeleccionado;
    this.familiarBeneficiarioDTO.sexo = this.sexoSeleccionado;
  
   var momentSTR = moment_(this.fhNacimiento, "YYYY/MM/DD").format("YYYY-MM-DD");
   this.familiarBeneficiarioDTO.fechaNaciomiento = momentSTR;
    this.applicationInitService.modificaFamiliaresPorUsuarioLogueado(this.familiarBeneficiarioDTO).subscribe (isSuccess => {
      console.log('Se guardo?  '+ isSuccess);
      this.blockUI.stop();

      if(isSuccess){
        formRegistro.reset();
        this.messageService.add({severity: 'success', summary: 'Operación exitosa', detail: 'El familiar se actualizo satisfactoriamente.'});
        this.router.navigate( ['familiarBeneficiario/todos']);
      }else{
        this.messageService.add({severity: 'error', summary: 'Error',
        detail: 'Ocurrió un error al actualizar los datos del familiar.' +
        ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
      }
    }, error =>{
      this.blockUI.stop();
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error al actualizar los datos del familiar.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
    });

  }

  closeDialog() {
    this.display = false;
  }


  @HostListener('window:beforeunload', ['$event'])
    unloadNotification($event: any) {
      this.router.navigate( ['familiarBeneficiario/todos']);
    }

}
