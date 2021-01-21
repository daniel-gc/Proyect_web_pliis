import {Component, forwardRef, OnInit} from '@angular/core';
import {DatePipe} from '@angular/common';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ConfirmationService, Message, MessageService} from 'primeng/api';
import {ApplicationInitService} from '../../../services/application-init.service';
import {RegistroService} from '../../../services/registro.service';
import {EmpleadoDTO} from '../../../models/generales/empleado-dto';
import {SexoDTO} from '../../../models/generales/sexo-dto';

import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {AfiliadoParaFamiliarDTO} from '../../../models/generales/afiliado-para-familiar-dto';
import {RelacionFamiliarDTO} from '../../../models/generales/relacion-familiar-dto';
import {NuevoFamiliarDTO} from '../../../models/generales/nuevo-familiar-dto';

@Component({
  selector: 'app-registro-beneficiario',
  templateUrl: './registro-beneficiario.component.html',
  styleUrls: ['./registro-beneficiario.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => RegistroBeneficiarioComponent),
            multi: true,
        }, DatePipe]
})
export class RegistroBeneficiarioComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService, private applicationInitService: ApplicationInitService,
                private registroService: RegistroService, private datePipe: DatePipe) {
        this.breadcrumbService.setItems([
            {label: 'Registro de nuevos beneficiarios', routerLink: ['/registroBeneficiarios']}
        ]);
    }

    afiliadoSeleccionado: AfiliadoParaFamiliarDTO;
    puedeRegistrarse = false;
    sexos: SexoDTO[] = [];
    sexoSeleccionado: SexoDTO;
    numTelef: any;
    catalogoRelacionesFamiliares: RelacionFamiliarDTO[] = [];
    display = false;
    pass0: string = null;
    pass1: string = null;
    match = true;
    msgs: Message[];
    uploadedFiles: any[] = [];
    es: any;

    idAfiliadoSeleccionado: number;
    displayDatos = false;
    puedeMostrarDialogo = false;
    nuevoFamiliar: NuevoFamiliarDTO = new NuevoFamiliarDTO();
    @BlockUI() blockUI: NgBlockUI;

    // public void;
    fechaNacPregunta: Date = new Date();
    relacionFamiliarSeleccionada: RelacionFamiliarDTO;

    ngOnInit() {
        this.blockUI.start('Cargando...');
        this.llenadoVariables();

        this.es = {
            firstDayOfWeek: 1,
            dayNames: ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'],
            dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
            dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
            monthNames: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre', 'octubre',
                'noviembre', 'diciembre'],
            monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
            today: 'Hoy',
            dateFormat: 'yy-mm-dd',
            clear: 'Borrar'
        };
    }

    llenadoVariables() {
        this.applicationInitService.getCatalogoSexo().subscribe(data3 => {
            this.sexos = data3;
            this.sexoSeleccionado = this.sexos[0];
            this.applicationInitService.getCatalogoRelacionesFamiliares().subscribe(relFam => {
                this.blockUI.stop();
                this.catalogoRelacionesFamiliares = relFam;
                this.relacionFamiliarSeleccionada = this.catalogoRelacionesFamiliares[0];
                this.catalogoRelacionesFamiliares = relFam;
                this.relacionFamiliarSeleccionada = this.catalogoRelacionesFamiliares[0];
            }, errorRelFam => {
                this.blockUI.stop();
                this.messageService.add({
                    severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                        ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'
                });
                this.catalogoRelacionesFamiliares = [];
            });
        }, error => {
            this.sexos = [];
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'
            });

        });
    }

    registrarUsuario() {
        this.blockUI.start('Guardando usuario...');
        // Llenar los datos faltantes para guardar familiar
        this.nuevoFamiliar.passw = this.pass0;
        this.nuevoFamiliar.idRelacionFamiliar = this.relacionFamiliarSeleccionada.idRelacionFamiliar;
        this.nuevoFamiliar.idSexo = this.sexoSeleccionado.idSexo;
        this.nuevoFamiliar.idAfiliado = this.idAfiliadoSeleccionado;

        this.applicationInitService.nuevoFamiliar(this.nuevoFamiliar).subscribe(msg => {
            this.blockUI.stop();
            this.messageService.add({severity: 'success', summary: 'Operación exitosa', detail: 'El familiar se creó satisfactoriamente.'});
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error', summary: 'Error', detail: 'Ocurrió un error al guardar el familiar: ' +
                    error.error.Mensaje
            });
        });
        // this.display = true;
    }

    closeDialog() {
        this.display = false;
    }

    mouseOutPass(event) {
        if (this.pass0 !== this.pass1) {
            this.match = false;
        } else {
            this.match = true;
        }
    }

    onUpload(event) {
        for (const file of event.files) {
            this.uploadedFiles.push(file);
        }

        this.msgs = [];
        this.msgs.push({severity: 'info', summary: 'File Uploaded', detail: ''});
    }

    onValidarRequisitos() {
        this.blockUI.start('Cargando...');
        const cS = this.idAfiliadoSeleccionado;

        this.applicationInitService.getAfiliadoIdAfiliado(cS)
            .subscribe(data => {
                if (data) {
                    this.puedeMostrarDialogo = true;
                    this.afiliadoSeleccionado = data;
                    this.displayDatos = true;
                    this.blockUI.stop();
                } else {
                    this.blockUI.stop();
                    this.puedeMostrarDialogo = false;
                    this.displayDatos = false;
                    this.puedeRegistrarse = false;
                    this.messageService.add({severity: 'error', summary: 'Error',
                        detail: 'No existe afiliado registrado en la plataforma con los datos solicitados'});
                }

            }, error => {
                this.blockUI.stop();
                this.puedeMostrarDialogo = false;
                this.displayDatos = false;
                this.puedeRegistrarse = false;
                this.afiliadoSeleccionado = null;
                this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda del afiliado'});
            });
    }

    closeDialogDatosSi() {
        const fechaTmp = this.datePipe.transform(this.fechaNacPregunta, 'yyyy-MM-dd');
        if (fechaTmp === this.afiliadoSeleccionado.fechaNacimiento.toString()) {
            this.displayDatos = false;
            this.puedeRegistrarse = true;
        } else {
            this.displayDatos = false;
            this.puedeRegistrarse = false;
            this.messageService.add({severity: 'error', summary: 'Error',
                detail: 'La fecha de nacimiento introducida no coincide con la del afiliado.'});
        }
    }

    closeDialogDatosNo() {
        this.displayDatos = false;
        this.puedeRegistrarse = false;
    }

}
