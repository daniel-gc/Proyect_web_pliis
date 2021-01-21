import { Component, OnInit } from '@angular/core';
import {ApplicationInitService} from '../../../../services/application-init.service';
import { FamiliarBeneficiarioDto } from 'src/app/models/familiar_beneficiario/familiar-beneficiario-dto';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import { NavigationExtras, Router } from '@angular/router';
import {MessageService} from 'primeng/api';
import domtoimage from 'dom-to-image';

@Component({
  selector: 'app-familiar-consulta',
  templateUrl: './familiar-consulta.component.html',
  styleUrls: ['./familiar-consulta.component.css'],
  providers: [MessageService]
})
export class FamiliarConsultaComponent implements OnInit {

  public listFamiliaresBeneficiario: FamiliarBeneficiarioDto[] = [];
  public familiarSelecionado : FamiliarBeneficiarioDto;
  @BlockUI() blockUI: NgBlockUI;
  cols: any[];

  constructor(private breadcrumbService: BreadcrumbService,
    private applicationInitService: ApplicationInitService,
    private router: Router,
    private messageService: MessageService) {
      
      this.breadcrumbService.setItems([
        { label: 'Consulta', routerLink: ['/familiarBeneficiario/todos'] }
      ]);

     }

  ngOnInit(): void {

    this.blockUI.start();

    this.cols = [
      {field: 'nombres', header: 'Nombre'},
      {field: 'apPaterno', header: 'Apellido Paterno'},
      {field: 'apMaterno', header: 'Apellido Materno'},
      {field: 'email', header: 'Email'},
      {field: 'fechaNaciomiento', header: 'Fecha de Nacimiento'},
      {field: 'sexo', header: 'Sexo'},
      {field: 'telefono', header: 'Telefono'},
      {field: 'curp', header: 'CURP'},
      {field: 'relacionFamiliar', header: 'Parentesco'}
  ];

    this.obtenerListaFamiliaresDeBeneficiario();
  }

  obtenerListaFamiliaresDeBeneficiario(){
    this.applicationInitService.consultaFamiliaresPorUsuarioLogueado().subscribe(listData => {
     this.listFamiliaresBeneficiario = listData;
     this.blockUI.stop();
    }, error => {
        this.blockUI.stop();
        this.listFamiliaresBeneficiario = [];
    
    });
  }

  modificarRegistro(familiarBeneficiarioDto: FamiliarBeneficiarioDto){
    console.log(familiarBeneficiarioDto);

    const navigationExtras: NavigationExtras = {state: {famBeneficiarioDTO: familiarBeneficiarioDto}};

    this.router.navigate( ['familiarBeneficiario/modificar'],navigationExtras );
  }

  eliminarRegistro(familiarBeneficiarioDto : FamiliarBeneficiarioDto){
    this.blockUI.start('Guardando...');
    this.applicationInitService.eliminarFamiliaresPorUsuarioLogueado(familiarBeneficiarioDto.idFamBeneficiario).subscribe (isSuccess => {
      console.log('Se guardo?  '+ isSuccess);

      if(isSuccess){
        this.messageService.add({severity: 'success', summary: 'Operación exitosa', detail: 'El familiar se elimino satisfactoriamente.'});
        this.obtenerListaFamiliaresDeBeneficiario()
      }else{
        this.blockUI.stop();
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error al eliminar los datos del familiar.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
      }

      
    }, error =>{
      this.blockUI.stop();
      this.messageService.add({severity: 'error', summary: 'Error',
              detail: 'Ocurrió un error al eliminar los datos del familiar.' +
              ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
    });

  }

  public takeScreen() {
    const node = document.getElementById('tablaResultado');
    if (node != null) {
        domtoimage.toJpeg(node, { quality: 0.95 }).then(dataUrl => {
            const link = document.createElement('a');
            link.download = 'mis-familiares.jpeg';
            link.href = dataUrl;
            link.click();
        });
    } else {
        console.log('Sin elementos');
    }
}



}
