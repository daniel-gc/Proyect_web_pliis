import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ApplicationInitService} from '../../../services/application-init.service';
import {SuscripcionDTO} from '../../../models/generales/suscripcion-dto';

@Component({
    selector: 'app-historial-pagos',
    templateUrl: './historial-pago.component.html',
    styleUrls: ['./historial-pago.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => HistorialPagoComponent),
            multi: true,
        }]
})
export class HistorialPagoComponent implements OnInit {
    constructor(
        private breadcrumbService: BreadcrumbService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private applicationInitService: ApplicationInitService) {
        this.breadcrumbService.setItems([
            {label: 'Historial de pagos', routerLink: ['/historialPago']}
        ]);
    }
    @BlockUI() blockUI: NgBlockUI;
    suscripciones: SuscripcionDTO[];
    selectedSuscripcion: SuscripcionDTO;
    cols: any[];

    ngOnInit() {
        this.cols = [
            // {field: 'pagoDTO.idPagoOpenpay', header: 'Folio de pago'},
            {field: 'fechaInicio', header: 'Inicio suscripción'},
            {field: 'fechaFin', header: 'Fin suscripción'},
            {field: 'planDTO.nombre', header: 'Plan'},
            {field: 'pagoDTO.description', header: 'Descripción'},
            {field: 'pagoDTO.monto', header: 'Cargo'},
        ];
        this.cargaHistorialPagos();
    }

    cargaHistorialPagos() {
        this.blockUI.start('Cargando datos...');
        this.applicationInitService.getHistorialPagos().subscribe(ret => {
            this.blockUI.stop();
            this.suscripciones = ret;
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador.'
            });
        });
    }
}
