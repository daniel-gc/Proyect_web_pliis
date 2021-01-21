import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {BreadcrumbService} from '../../../../breadcrumb.service';
import {ApplicationInitService} from '../../../../services/application-init.service';
import {RegistroService} from '../../../../services/registro.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {FamiliarDTO} from '../../../../models/generales/familiar-dto';

@Component({
    selector: 'app-beneficiarios',
    templateUrl: './beneficiarios.component.html',
    styleUrls: ['./beneficiarios.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => BeneficiariosComponent),
            multi: true,
        }, DatePipe]
})
export class BeneficiariosComponent implements OnInit {
    @BlockUI() blockUI: NgBlockUI;
    catalogoMisFamiliares: FamiliarDTO[] = [];
    selectedFamiliar: FamiliarDTO = new FamiliarDTO();
    cols: any[];

    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService, private applicationInitService: ApplicationInitService,
                private registroService: RegistroService, private datePipe: DatePipe) {
        this.breadcrumbService.setItems([
            {label: 'Gestión de beneficiarios', routerLink: ['/beneficiarios']}
        ]);
    }

    ngOnInit() {
        this.cols = [
            {field: 'idVinculoFamiliar', header: '#'},
            {field: 'nombres', header: 'Nombre(s)'},
            {field: 'apellidoPaterno', header: 'Apellido'},
        ];
        this.cargarFamiliares();
    }

    deleteFamiliar(fam: FamiliarDTO) {
        this.blockUI.start('Cargando datos...');
        this.applicationInitService.cancelarFamiliar(fam.idVinculoFamiliar).subscribe(ret => {
            this.blockUI.stop();
            this.cargarFamiliares();
            this.messageService.add({severity: 'success',
                summary: 'Operación exitosa', detail: 'Se eliminó el familiar seleccionado: ' + this.selectedFamiliar.nombres});
        }, error => {
            this.blockUI.stop();
            this.messageService.add({severity: 'error', summary: 'Error',
                detail: 'Ocurrió un error al intentar borrar el familiar: ' + error.error.Mensaje
            });
        });
    }

    cargarFamiliares() {
        this.blockUI.start('Cargando datos...');
        this.applicationInitService.getTodosMisFamiliares().subscribe(ret => {
            this.blockUI.stop();
            this.catalogoMisFamiliares = ret;
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador.'
            });
        });
    }

    confirm(fam: FamiliarDTO) {
        this.selectedFamiliar = fam;
        this.confirmationService.confirm({
            message: '¿Está seguro de eliminar el familiar seleccionado? \n' + fam.nombres + ' ' + fam.apellidoPaterno,
            accept: () => {
                this.deleteFamiliar(fam);
            }
        });
    }
}
