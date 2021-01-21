import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ApplicationInitService} from '../../../services/application-init.service';
import {FiniquitoCalculoDTO} from '../../../models/generales/calculo-finiquito-dto';
import {DatePipe} from '@angular/common';
import {RegistroService} from '../../../services/registro.service';
import {FiniquitoDetalleDTO} from '../../../models/generales/detalle-Finiquito-dto';

@Component({
    selector: 'app-calculo-finiquito',
    templateUrl: './calculo-finiquito.component.html',
    styleUrls: ['./calculo-finiquito.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CalculoFiniquitoComponent),
            multi: true,
        }, DatePipe ]
})
export class CalculoFiniquitoComponent implements OnInit {
    constructor(
        private breadcrumbService: BreadcrumbService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private applicationInitService: ApplicationInitService,
        private datePipe: DatePipe) {
        this.breadcrumbService.setItems([
            {label: 'Calculo de finiquito', routerLink: ['/calculoFiniquito']}
        ]);
    }

    @BlockUI() blockUI: NgBlockUI;
    es: any;
    finiquitoCalculoDTO: FiniquitoCalculoDTO;
    finiquitoDetalleDTO: FiniquitoDetalleDTO;
    mostrarFiniquitoDetalle = false;

    ngOnInit() {
        this.finiquitoCalculoDTO = new FiniquitoCalculoDTO();
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
    }

    onCalcularFiniquito() {
        this.blockUI.start('Cargando...');
        this.applicationInitService.calcularFiniquito(this.finiquitoCalculoDTO).subscribe(data0 => {
            this.finiquitoDetalleDTO = data0;
            this.blockUI.stop();
            this.mostrarFiniquitoDetalle = true;
        }, error => {
            this.blockUI.stop();
            this.finiquitoDetalleDTO = null;
            this.mostrarFiniquitoDetalle = false;
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
    }
}
