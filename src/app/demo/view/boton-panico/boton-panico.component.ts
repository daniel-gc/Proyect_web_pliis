import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ConfirmationService, Message, MessageService} from 'primeng/api';
import {PanicoService} from '../../../services/comunicaciones/panico/panico.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {PanicoDTO} from '../../../models/panico/panico-dto';
import {EmpresaDTO} from '../../../models/generales/empresa-dto';
import {ApplicationInitService} from '../../../services/application-init.service';
import {CentroTrabajoDTO} from '../../../models/generales/centro-trabajo-dto';

@Component({
  selector: 'app-boton-panico',
  templateUrl: './boton-panico.component.html',
  styleUrls: ['./boton-panico.component.css'],
  providers: [MessageService,
      ConfirmationService]
})
export class BotonPanicoComponent implements OnInit {
    selectedCausas: string[] = [];
    msgs: Message[] = [];
    @BlockUI() blockUI: NgBlockUI;
    panicoDTO: PanicoDTO = new PanicoDTO();
    listaEmpresas: EmpresaDTO[] = [];
    empresaSeleccionada: EmpresaDTO;
    centroTrabajoSeleccionado: CentroTrabajoDTO;
    aceptoTerminos = false;

    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService, private panicoService: PanicoService,
                private applicationInitService: ApplicationInitService) {
        this.breadcrumbService.setItems([
            { label: 'Boton de Pánico', routerLink: ['/panico'] }
        ]);
    }

  ngOnInit() {
      this.blockUI.start('Cargando datos iniciales');
      this.applicationInitService.getCatalogoEmpresa().subscribe(data => {
            this.listaEmpresas = data;
            this.empresaSeleccionada = this.listaEmpresas[0];
            if (this.empresaSeleccionada.centrosTrabajo.length > 0) {
                this.centroTrabajoSeleccionado = this.empresaSeleccionada.centrosTrabajo[0];
            } else {
                this.centroTrabajoSeleccionado = undefined;
            }
            this.blockUI.stop();
        }, error => {
            this.listaEmpresas = [];
            this.empresaSeleccionada = null;
            this.centroTrabajoSeleccionado = undefined;
            this.blockUI.stop();
        });
  }

    onTabChange(event) {
        // this.messageService.add({severity: 'info', summary: 'Tab Expanded', detail: 'Index: ' + event.index});
    }

    confirm1() {
        this.confirmationService.confirm({
            message: '¿Está seguro de proceder con la denuncia formulada?',
            header: 'Confirmación de denuncia',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.blockUI.start('Procesando su petición');
                this.panicoDTO.esIdCentroTrabajo = this.centroTrabajoSeleccionado.idCentroTrabajo;
                this.panicoDTO.tipoPanicos = this.selectedCausas;
                this.panicoDTO.nombreEmpresa = this.empresaSeleccionada.nombre;
                this.panicoDTO.nombreCentroTrabajo = this.centroTrabajoSeleccionado.nombre;

                this.panicoService.crearPanico(this.panicoDTO).subscribe(data => {
                    if (data === true) {
                        // this.msgs = [{severity: 'info', summary: 'Confirmación:', detail: 'Su denuncia ha sido enviada'}];
                        this.messageService.add({severity: 'info', summary: 'Confirmado', detail: 'Su denuncia ha sido enviada'});
                        this.blockUI.stop();
                    } else {
                        this.messageService.add({severity: 'error', summary: 'Error',
                            detail: 'El centro de trabajo seleccionado no tiene delegado asignado'});
                        this.blockUI.stop();
                    }
                }, error => {
                    // this.msgs = [{severity: 'error', summary: 'Error:', detail: 'Ocurrió un error'}];
                    this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error'});
                    this.blockUI.stop();
                });
            },
            reject: () => {
                // this.msgs = [{severity: 'info', summary: 'Cancelación:', detail: 'Usted ha cancelado la denuncia'}];
                this.messageService.add({severity: 'warn', summary: 'Cancelada', detail: 'Usted ha cancelado la denuncia'});
            }
        });
    }

    onChangeEmpresas(event) {
        if (event.value) {
            if (this.empresaSeleccionada.centrosTrabajo.length > 0) {
                this.centroTrabajoSeleccionado = this.empresaSeleccionada.centrosTrabajo[0];
            } else {
                this.centroTrabajoSeleccionado = undefined;
            }
        }


    }
}
