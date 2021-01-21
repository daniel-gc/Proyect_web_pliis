import {Component, OnInit, ViewChild, forwardRef} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {SignaturePad} from 'angular2-signaturepad/signature-pad';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {ControlValueAccessor} from '@angular/forms';
import {ApplicationInitService} from '../../../services/application-init.service';
import {EmpresaDTO} from '../../../models/generales/empresa-dto';
import {EmpleadoDTO} from '../../../models/generales/empleado-dto';
import {RegistroService} from '../../../services/registro.service';
import {NuevoMiembroArquitecturaDTO} from '../../../models/generales/nuevo-miembro-arquitectura-dto';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {SexoDTO} from '../../../models/generales/sexo-dto';
import {NacionalidadDTO} from '../../../models/generales/nacionalidad-dto';
import {EstadoCivilDTO} from '../../../models/generales/estado-civil-dto';
import {CentroTrabajoDTO} from '../../../models/generales/centro-trabajo-dto';
import {MensajeDTO} from '../../../models/generales/mensaje-dto';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'app-registro-miembro',
    templateUrl: './registro-miembro.component.html',
    styleUrls: ['./registro-miembro.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => RegistroMiembroComponent),
            multi: true,
        }, DatePipe ]
})
export class RegistroMiembroComponent implements OnInit {
    constructor(private breadcrumbService: BreadcrumbService, private messageService: MessageService,
                private confirmationService: ConfirmationService, private applicationInitService: ApplicationInitService,
                private registroService: RegistroService, private datePipe: DatePipe) {
        this.breadcrumbService.setItems([
            {label: 'Registro de nuevos usuarios', routerLink: ['/registro']}
        ]);
    }

    empleadoSeleccionado: EmpleadoDTO;
    puedeRegistrarse = true;
    sexos: SexoDTO[] = [];
    sexoSeleccionado: SexoDTO;
    numTelef: any;
    nacionalidadSeleccionada: NacionalidadDTO;
    catalogoNacionalidad: NacionalidadDTO[] = [];
    estadoCivilSeleccionado: EstadoCivilDTO;
    catalogoEstadoCivil: EstadoCivilDTO[] = [];
    display = false;
    pass0: string = null;
    pass1: string = null;
    match = true;
    msgs: Message[];
    uploadedFiles: any[] = [];
    es: any;
    catalogoEmpresa: EmpresaDTO[]  = [];
    empresaSeleccionada: EmpresaDTO;
    catalogoCentroTrabajo: CentroTrabajoDTO[] = [];
    centroTrabajoSeleccionado: CentroTrabajoDTO;
    rfcSeleccionado: string;
    displayDatos = false;
    puedeMostrarDialogo = false;
    nuevoMiembroArquitecturaDTO: NuevoMiembroArquitecturaDTO;
    @BlockUI() blockUI: NgBlockUI;
    fechaAntiguedad: Date;
    fechaNacimiento: Date;
    fechaNacPregunta: Date;

    public void;

    ngOnInit() {
        this.blockUI.start('Cargando...');
        this.nuevoMiembroArquitecturaDTO = new NuevoMiembroArquitecturaDTO();
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
            clear: 'Borrar',
            dateFormat: 'dd/mm/yy'
        };
    }

    llenadoVariables() {
        this.applicationInitService.getCatalogoEmpresa().subscribe(data0 => {
            this.catalogoEmpresa = data0;
            this.empresaSeleccionada = this.catalogoEmpresa[0];
            this.applicationInitService.getCatalogoEstadoCivil().subscribe(data1 => {
                this.catalogoEstadoCivil = data1;
                this.estadoCivilSeleccionado = this.catalogoEstadoCivil[0];
                this.applicationInitService.getCatalogoNacionalidad().subscribe(data2 => {
                    this.catalogoNacionalidad = data2;
                    this.nacionalidadSeleccionada = this.catalogoNacionalidad[0];
                    this.applicationInitService.getCatalogoSexo().subscribe(data3 => {
                        this.blockUI.stop();
                        this.sexos = data3;
                        this.sexoSeleccionado = this.sexos[0];
                    }, error => {
                        this.blockUI.stop();
                        this.sexos = [];
                        this.messageService.add({severity: 'error', summary: 'Error',
                                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                                ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});

                    });
                }, error => {
                    this.blockUI.stop();
                    this.catalogoNacionalidad = [];
                    this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la ' +
                            'búsqueda de datos requeridos.' +
                            ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});

                });
            }, error => {
                this.blockUI.stop();
                this.catalogoEstadoCivil = [];
                this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la ' +
                        'búsqueda de datos requeridos.' +
                        ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});

            });
            // this.blockUI.stop();
        }, error => {
            this.blockUI.stop();
            this.catalogoEmpresa = [];
            this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la ' +
                    'búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
    }

    registrarUsuario() {
        this.blockUI.start('Guardando usuario...');
        this.nuevoMiembroArquitecturaDTO.roles = ['ROLE_MIEMBRO_REGISTRADO'];
        // this.nuevoMiembroArquitecturaDTO.empresa = this.nuevoMiembroArquitecturaDTO.empresa;
        // this.nuevoMiembroArquitecturaDTO.centroTrabajo = this.nuevoMiembroArquitecturaDTO.centroTrabajo;
        //this.nuevoMiembroArquitecturaDTO.sindicato = null;
        this.nuevoMiembroArquitecturaDTO.idEstadoCivil = this.estadoCivilSeleccionado.idEstadoCivil;
        this.nuevoMiembroArquitecturaDTO.idNacionalidad = this.nacionalidadSeleccionada.idNacionalidad;
        this.nuevoMiembroArquitecturaDTO.idSexo = this.sexoSeleccionado.idSexo;
        this.nuevoMiembroArquitecturaDTO.fechaRegistro = new Date();
        this.nuevoMiembroArquitecturaDTO.nick = this.nuevoMiembroArquitecturaDTO.email;
        this.nuevoMiembroArquitecturaDTO.fotoCredencial = null;
        this.nuevoMiembroArquitecturaDTO.fechaAfiliacion = null;
        this.nuevoMiembroArquitecturaDTO.fotoAfiliado = null;
        this.nuevoMiembroArquitecturaDTO.contrato = null;//this.empleadoSeleccionado.contrato;
        this.nuevoMiembroArquitecturaDTO.fechaBaja = null;
        //this.nuevoMiembroArquitecturaDTO.salarioMensualBruto = null;
        this.nuevoMiembroArquitecturaDTO.fechaIngresoEmpresa = this.fechaAntiguedad;
        this.nuevoMiembroArquitecturaDTO.fechaNacimiento = this.fechaNacimiento;
        this.nuevoMiembroArquitecturaDTO.passw = this.pass0;
        this.nuevoMiembroArquitecturaDTO.direccionDomicilio = this.nuevoMiembroArquitecturaDTO.calle + ' ' +
            this.nuevoMiembroArquitecturaDTO.numero + ' ' + this.nuevoMiembroArquitecturaDTO.colonia + ' ' +
            this.nuevoMiembroArquitecturaDTO.alcaldia + ' ' + this.nuevoMiembroArquitecturaDTO.ciudad + ' ' +
            this.nuevoMiembroArquitecturaDTO.pais;

        this.applicationInitService.guardarNuevoMiembro(this.nuevoMiembroArquitecturaDTO).subscribe(msg => {
            this.blockUI.stop();
            this.messageService.add({severity: 'success', summary: 'Operación exitosa', detail: 'El usuario se creó satisfactoriamente.'});
        }, error => {
            this.blockUI.stop();
            let mensajesErrorM = '';
            if (error.error.Mensaje !== undefined) {
                for (let i = 0; i < error.error.Mensaje.length; i++) {
                    if ((i + 1) === error.error.Mensaje.length) {
                        mensajesErrorM += error.error.Mensaje[i];
                    } else {
                        mensajesErrorM += error.error.Mensaje[i] + ', ';
                    }
                }
            } else {
                mensajesErrorM = error.error.mensaje;
            }
            this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error al guardar el usuario: ' + mensajesErrorM});
        });
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
        const cS = this.rfcSeleccionado;
        const idEmpresa = this.empresaSeleccionada.idEmpresa;

        this.registroService.getUsuario(idEmpresa, cS)
            .subscribe(data => {
                if (data) {
                    this.blockUI.stop();
                    this.puedeMostrarDialogo = true;
                    this.empleadoSeleccionado = data;
                    this.llenarDatosInicialesEmpleado(this.empleadoSeleccionado, this.nuevoMiembroArquitecturaDTO);
                    this.displayDatos = true;
                    this.blockUI.start('Cargando...');
                    this.applicationInitService.getCatalogoCentroTrabajo(this.empresaSeleccionada.idEmpresa).subscribe(data4 => {
                        this.blockUI.stop();
                        this.catalogoCentroTrabajo = data4;
                        this.centroTrabajoSeleccionado = this.catalogoCentroTrabajo[0];
                        this.centroTrabajoSeleccionado = this.catalogoCentroTrabajo.find(val => {
                            return val.idCentroTrabajo === this.empleadoSeleccionado.idLugarTrabajo;
                        });
                    }, error => {
                        this.blockUI.stop();
                        this.catalogoCentroTrabajo = [];
                        this.messageService.add({severity: 'error', summary: 'Error',
                            detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                            ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
                    });
                } else {
                    this.blockUI.stop();
                    this.puedeMostrarDialogo = false;
                    this.displayDatos = false;
                    this.puedeRegistrarse = false;
                    this.messageService.add({severity: 'error', summary: 'Error', detail: 'No existe empleado con los datos solicitados'});
                }

            }, error => {
                this.blockUI.stop();
                this.puedeMostrarDialogo = false;
                this.displayDatos = false;
                this.puedeRegistrarse = false;
                this.empleadoSeleccionado = null;
                this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error en la búsqueda del empleado'});
            });
    }

    closeDialogDatosSi() {
        this.displayDatos = false;
        this.puedeRegistrarse = true;
    }

    closeDialogDatosNo() {
        this.displayDatos = false;
        this.puedeRegistrarse = false;
    }
    llenarDatosInicialesEmpleado(empleadoDTO: EmpleadoDTO, nuevoMiembroArquitecturaDTO: NuevoMiembroArquitecturaDTO){
        nuevoMiembroArquitecturaDTO.nombres = empleadoDTO.nombre;
        nuevoMiembroArquitecturaDTO.curp = empleadoDTO.curp;
        nuevoMiembroArquitecturaDTO.rfc = empleadoDTO.rfc;
        nuevoMiembroArquitecturaDTO.salarioMensualNeto = empleadoDTO.sueldo;
        // this.fechaNacimiento = this.datePipe.transform(empleadoDTO.fechaNacimiento, 'dd/mm/yy');
        // this.fechaNacimiento = empleadoDTO.fechaNacimiento;
        // nuevoUsuarioArquitecturaDTO.fechaNacimiento = empleadoDTO.fechaNacimiento;
        // nuevoUsuarioArquitecturaDTO.fechaIngresoEmpresa = empleadoDTO.fechaAntiguedad;
        let ff = this.datePipe.transform(empleadoDTO.fechaNacimiento, 'yyyy-MM-dd');
        this.fechaNacimiento = new Date(ff);
        ff = this.datePipe.transform(empleadoDTO.fechaAntiguedad, 'yyyy-MM-dd', 'GTM-5');
        this.fechaAntiguedad  = new Date(ff);

    }

//     private llenarDropDowns() {
//         this.sexoSeleccionado = this.sexos[0];
//         this.nacionalidadSeleccionada = this.catalogoNacionalidad[0];
//         this.estadoCivilSeleccionado = this.catalogoEstadoCivil[0];
//         this.centroTrabajoSeleccionado = this.catalogoCentroTrabajo[0];
//     }
    closeDialogDatosSiFechaOk() {
        const fechaTmp = this.datePipe.transform(this.fechaNacPregunta, 'yyyy-MM-dd');
        if (fechaTmp === this.empleadoSeleccionado.fechaNacimiento.toString()) {
            this.displayDatos = false;
            this.puedeRegistrarse = true;
        } else {
            this.displayDatos = false;
            this.puedeRegistrarse = false;
            this.messageService.add({severity: 'error', summary: 'Error',
                detail: 'La fecha de nacimiento introducida no coincide con la del afiliado.'});
        }
    }
}


