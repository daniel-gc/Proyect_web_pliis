import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {FileUploadModule} from 'primeng/fileupload';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {DialogModule} from 'primeng/dialog';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ApplicationInitService} from '../../../services/application-init.service';
import {NuevaSolicitudCreditoDTO} from '../../../models/generales/nueva-solicitud-credito-dto';
import {DatePipe} from '@angular/common';
import {MontoCreditoDTO} from '../../../models/generales/monto-credito-dto';
import {TipoArchivoDTO} from '../../../models/generales/tipo-archivo-dto';
import {EstatusCreditoDTO} from '../../../models/generales/estatus-credito-dto';
import {Router} from '@angular/router';
import {TokenService} from '../../../services/security/token.service';
import {ContactoCreditoDTO} from '../../../models/generales/contacto-credito-dto';
import {ArchivoCreditoDTO} from '../../../models/generales/archivo-credito-dto';
import {ObservacionCreditoDTO} from '../../../models/generales/observacion-credito-dto';
import {GalleriaModule} from 'primeng/galleria';

@Component({
    selector: 'app-credito',
    templateUrl: './credito.component.html',
    styleUrls: ['./credito.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CreditoComponent),
            multi: true,
        }, DatePipe
    ]
})
export class CreditoComponent implements OnInit {
    constructor(
        private breadcrumbService: BreadcrumbService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private applicationInitService: ApplicationInitService,
        private tokenService: TokenService, ) {
        this.breadcrumbService.setItems([
            {label: 'Crédito', routerLink: ['/credito']}
        ]);
    }

    nuevaSolicitudCreditoDTO: NuevaSolicitudCreditoDTO;
    es: any;
    cols: any[];
    arregloIdArchivo: number[];
    uploadedFiles: any[] = [];
    @BlockUI() blockUI: NgBlockUI;
    fechaNacimiento: Date;
    fechaAntiguedad: Date;
    yearRange: string = (new Date().getFullYear() - 100) + ':' + (new Date().getFullYear());
    tipoUsuario: string;
    mostrarLista: boolean;
    muestraDetalle: boolean;
    catalogoMontoCredito: MontoCreditoDTO[]  = [];
    catalogoMontoCreditoFiltrado: MontoCreditoDTO[] = [];
    catalogoTipoArchivo: TipoArchivoDTO[]  = [];
    catalogoEstatusCreditoDTO: EstatusCreditoDTO[]  = [];
    montoCreditoSeleccionado: MontoCreditoDTO;
    tipoArchivoSeleccionado: TipoArchivoDTO;
    estatusCreditoSeleccionado: EstatusCreditoDTO;
    solicitudesCredito: NuevaSolicitudCreditoDTO[];
    selectedSolicitudesCredito: NuevaSolicitudCreditoDTO;
    displayCambioEstatus = false;
    puedeMostrarDialogo = false;
    displayVistaPrevia = false;
    puedeMostrarDialogoImg = false;
    observacion: string;
    nuevaObservacionCreditoDTO: ObservacionCreditoDTO;
    disableMonto = true;
    selectedImageIndex = -1;
    bloquearGuardado = false;
    bloquearGuardadoConcluida = false;
    mailValidoForm = false;
    telefonoValidoForm = false;
    formularioValido = false;
    errorForm = false;
    formuObservacionValido = false;
    esAnalista = false;
    existeSolicitudCreditoPendiente = false;
    tooltipBtnNueva = 'Nueva Solicitud';
    errorText: string;
    sortOrder = 1;
    imageObject: Array<object> = [
        { image: './assets/layout/images/image-not-found.svg', }
    ];
    hoy: Date = new Date();
    antiguedad: number [];
    archivosCargados = false;
    urlArchivo0: string | ArrayBuffer;
    tipoArchivo0 = '';
    urlArchivo1: string | ArrayBuffer;
    tipoArchivo1 = '';
    urlArchivo2: string | ArrayBuffer;
    tipoArchivo2 = '';
    urlArchivo3: string | ArrayBuffer;
    tipoArchivo3 = '';
    urlArchivo4: string | ArrayBuffer;
    tipoArchivo4 = '';

    ngOnInit() {
        this.llenadoVariables();
        this.consultaSolicitudesCredito('');
    }

    llenadoVariables() {
        this.blockUI.start('Cargando...');
        this.arregloIdArchivo = [1, 1, 1, 2, 4];
        if (this.tokenService.getAuthorities().indexOf('ROLE_AFILIADO') !== -1) {
            this.tipoUsuario = 'AS';
        } else if (this.tokenService.getAuthorities().indexOf('ROLE_MIEMBRO_ACTIVO') !== -1) {
            this.tipoUsuario = 'UE';
        }
        if (this.tokenService.getAuthorities().indexOf('ROLE_ANALISTA_CREDITO') !== -1
        || this.tokenService.getAuthorities().indexOf('ROLE_ANALISTA_ADMIN') !== -1) {
            this.esAnalista = true;
            this.sortOrder = -1;
            if (this.tokenService.getAuthorities().indexOf('ROLE_ANALISTA_CREDITO') !== -1){
                this.tipoUsuario = 'AC';
            }
            if (this.tokenService.getAuthorities().indexOf('ROLE_ANALISTA_ADMIN') !== -1){
                this.tipoUsuario = 'AA';
            }
        }
        this.cols = [
            {field: 'idCredito', header: 'Folio'},
            {field: 'fechaSolicitud', header: 'Fecha solicitud'},
            {field: 'nombre', header: 'Nombre solicitante'},
            {field: 'empresa', header: 'Empresa'},
            {field: 'numeroEmpleado', header: 'Numero empleado'},
            {field: 'montoCreditoDTO.monto', header: 'Monto Solicitado'},
            {field: 'estatusCreditoDTO.descripcion', header: 'Estatus'},
        ];
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

        this.applicationInitService.getCatalogoMontoCredito().subscribe(data0 => {
            this.catalogoMontoCredito = data0;
            for (const montoCredito of this.catalogoMontoCredito) {
                const montoStr = this.formatNumber(montoCredito.monto, false);
                montoCredito.montoStr = montoStr;
            }
            // this.montoCreditoSeleccionado = this.catalogoMontoCredito[0];
            this.applicationInitService.getCatalogoTipoArchivo().subscribe(data1 => {
                this.catalogoTipoArchivo = data1;
                // this.tipoArchivoSeleccionado = this.catalogoTipoArchivo[0];
                this.applicationInitService.getCatalogoEstatusCredito().subscribe(data2 => {
                    this.blockUI.stop();
                    this.catalogoEstatusCreditoDTO = data2;
                    // this.estatusCreditoSeleccionado = this.catalogoEstatusCreditoDTO[0];
                }, error => {
                    this.blockUI.stop();
                    this.catalogoEstatusCreditoDTO = [];
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                            ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
                });
            }, error => {
                this.blockUI.stop();
                this.catalogoTipoArchivo = [];
                this.messageService.add({
                    severity: 'error',
                    summary: 'Error',
                    detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
            });
        }, error => {
            this.blockUI.stop();
            this.catalogoMontoCredito = [];
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
        this.mostrarLista = true;
        this.muestraDetalle = false;
    }

    onRegresarAListado(origen: string) {
        this.ngOnInit();
        this.nuevaSolicitudCreditoDTO = null;
        this.mostrarLista = true;
        this.muestraDetalle = false;
        this.consultaSolicitudesCredito(origen);
    }

    consultaSolicitudesCredito(origen: string) {
        this.blockUI.start('Consultando solicitudes...');
        this.applicationInitService.consultaSolicitudesCredito().subscribe(ret => {
            this.solicitudesCredito = ret;
            if (!this.esAnalista) {
                for (const solicitudCredito of this.solicitudesCredito) {
                    if (!(solicitudCredito.estatusCreditoDTO.idEstatusCredito === 7 ||
                        solicitudCredito.estatusCreditoDTO.idEstatusCredito === 8)) {
                        this.existeSolicitudCreditoPendiente = true;
                        this.tooltipBtnNueva = 'Existe una solicitud de crédito en proceso';
                    }
                }
                if (!this.existeSolicitudCreditoPendiente) {
                    this.tooltipBtnNueva = 'Nueva Solicitud';
                }
            }
            this.blockUI.stop();
        }, error => {
            this.blockUI.stop();
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador.'
            });
        });
        if (origen !== '') {
            this.mostrarMensajesLista(origen);
        }
    }

    onMuestraFormDetalle(origen: string, SolicitudCredito: NuevaSolicitudCreditoDTO) {
        let mostrarListaTemp = true;
        let muestraDetalleTemp = false;
        if (origen === 'nueva') {
            if (this.existeSolicitudCreditoPendiente) {
                this.mostrarMensajesLista('mEspera');
                mostrarListaTemp = true;
                muestraDetalleTemp = false;
            } else {
                this.blockUI.start('Cargando solicitud...');
                this.nuevaSolicitudCreditoDTO = new NuevaSolicitudCreditoDTO();
                this.nuevaSolicitudCreditoDTO.correoAfilNoValido = true;
                this.nuevaSolicitudCreditoDTO.telefonoAfilNoValido = true;

                const contactoCreditoDTO1 = new ContactoCreditoDTO();
                contactoCreditoDTO1.correoContactoNoValido = true;
                contactoCreditoDTO1.telefonoContactoNoValido = true;
                const contactoCreditoDTO2 = new ContactoCreditoDTO();
                contactoCreditoDTO2.correoContactoNoValido = true;
                contactoCreditoDTO2.telefonoContactoNoValido = true;

                this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO.push(contactoCreditoDTO1);
                this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO.push(contactoCreditoDTO2);

                for (const idArchivo of this.arregloIdArchivo) {
                    for (const tipoArchivo of this.catalogoTipoArchivo) {
                        if (idArchivo === tipoArchivo.idTipoArchivo) {
                            const archivoCreditoDTO = new ArchivoCreditoDTO();
                            archivoCreditoDTO.idTipoArchivo = idArchivo;
                            archivoCreditoDTO.tipoArchivoDTO = tipoArchivo;
                            archivoCreditoDTO.botonDisabled1 = false;
                            archivoCreditoDTO.botonDisabled2 = true;
                            archivoCreditoDTO.botonDisabled3 = true;
                            this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO.push(archivoCreditoDTO);
                        }
                    }
                }
                this.fechaNacimiento = null;
                this.fechaAntiguedad = null;
                this.montoCreditoSeleccionado = null;
                this.bloquearGuardado = false;
                mostrarListaTemp = false;
                muestraDetalleTemp = true;
            }
        } else if (origen === 'modifica') {
            this.blockUI.start('Cargando solicitud...');
            this.nuevaSolicitudCreditoDTO = SolicitudCredito;
            this.fechaNacimiento = this.nuevaSolicitudCreditoDTO.fechaNacimiento;
            this.fechaAntiguedad = new Date(this.nuevaSolicitudCreditoDTO.fechaIngresoCia);
            this.validarMonto();

            this.esEmailValido('correoAfil', 0);
            this.esEmailValido('correoContacto', 0);
            this.esEmailValido('correoContacto', 1);
            this.esTelefonoValido('telefonoAfil', 0);
            this.esTelefonoValido('telefonoContacto', 0);
            this.esTelefonoValido('telefonoContacto', 1);

            for (const archivoCreditoDTO of this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO) {
                archivoCreditoDTO.botonDisabled1 = true;
                archivoCreditoDTO.botonDisabled2 = true;
                archivoCreditoDTO.botonDisabled3 = false;
            }

            this.montoCreditoSeleccionado = this.nuevaSolicitudCreditoDTO.montoCreditoDTO;
            if (this.nuevaSolicitudCreditoDTO.estatusCreditoDTO.idEstatusCredito === 7 ||
                this.nuevaSolicitudCreditoDTO.estatusCreditoDTO.idEstatusCredito === 8) {
                this.bloquearGuardado = true;
                this.bloquearGuardadoConcluida = true;
            } else {
                if (this.tokenService.getAuthorities().indexOf('ROLE_ANALISTA_CREDITO') !== -1) {
                    this.bloquearGuardado = true;
                } else {
                    this.bloquearGuardado = false;
                }
                this.bloquearGuardadoConcluida = false;
            }
            mostrarListaTemp = false;
            muestraDetalleTemp = true;
        }
        this.mostrarLista = mostrarListaTemp;
        this.muestraDetalle = muestraDetalleTemp;
        this.blockUI.stop();
    }

    onSelectFile(event) {
        const idStr = event.target.id;
        const idArchivo = parseInt(idStr.substr(4, idStr.length), 10);

        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled1 = true;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled2 = false;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled3 = true;

        if (event.target.files && event.target.files[0]) {
            const tipoArchivo = event.target.files[0].type;

            if (idArchivo === 0) {
                this.tipoArchivo0 = tipoArchivo;
                const reader = new FileReader();
                reader.readAsDataURL(event.target.files[0]); // read file as data url
                reader.onload = (event) => { // called once readAsDataURL is completed
                    this.urlArchivo0 = event.target.result;
                };
            } else if (idArchivo === 1) {
                this.tipoArchivo1 = tipoArchivo;
                const reader = new FileReader();
                reader.readAsDataURL(event.target.files[0]); // read file as data url
                reader.onload = (event) => { // called once readAsDataURL is completed
                    this.urlArchivo1 = event.target.result;
                };
            } else if (idArchivo === 2) {
                this.tipoArchivo2 = tipoArchivo;
                const reader = new FileReader();
                reader.readAsDataURL(event.target.files[0]); // read file as data url
                reader.onload = (event) => { // called once readAsDataURL is completed
                    this.urlArchivo2 = event.target.result;
                };
            } else if (idArchivo === 3) {
                this.tipoArchivo3 = tipoArchivo;
                const reader = new FileReader();
                reader.readAsDataURL(event.target.files[0]); // read file as data url
                reader.onload = (event) => { // called once readAsDataURL is completed
                    this.urlArchivo3 = event.target.result;
                };
            } else if (idArchivo === 4) {
                this.tipoArchivo4 = tipoArchivo;
                const reader = new FileReader();
                reader.readAsDataURL(event.target.files[0]); // read file as data url
                reader.onload = (event) => { // called once readAsDataURL is completed
                    this.urlArchivo4 = event.target.result;
                };
            }
        }
    }

    onUpload(rowIndex) {
        const idArchivo = rowIndex;
        let tipoArchivo = '';
        let urlArchivo: string | ArrayBuffer = '';

        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled1 = true;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled2 = true;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled3 = false;

        if (idArchivo === 0) {
            tipoArchivo = this.tipoArchivo0;
            urlArchivo = this.urlArchivo0;
            this.tipoArchivo0 = '';
            this.urlArchivo0 = '';
        } else if (idArchivo === 1) {
            tipoArchivo = this.tipoArchivo1;
            urlArchivo = this.urlArchivo1;
            this.tipoArchivo1 = '';
            this.urlArchivo1 = '';
        } else if (idArchivo === 2) {
            tipoArchivo = this.tipoArchivo2;
            urlArchivo = this.urlArchivo2;
            this.tipoArchivo2 = '';
            this.urlArchivo2 = '';
        } else if (idArchivo === 3) {
            tipoArchivo = this.tipoArchivo3;
            urlArchivo = this.urlArchivo3;
            this.tipoArchivo3 = '';
            this.urlArchivo3 = '';
        } else if (idArchivo === 4) {
            tipoArchivo = this.tipoArchivo4;
            urlArchivo = this.urlArchivo4;
            this.tipoArchivo4 = '';
            this.urlArchivo4 = '';
        }

        if (tipoArchivo === 'image/jpg' || tipoArchivo === 'image/jpeg' || tipoArchivo === 'image/png') {
            const strQuitar = 'data:' +  tipoArchivo + ';base64,';
            const urlArchivoFinal = (urlArchivo + '').replace(strQuitar, '');
            // const archivoArray = this.b64toBlob(urlArchivoFinal, tipoArchivo, 'Expediente');
            for (let i = 0; i < 3; i++) {
                this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].extencion = tipoArchivo;
                this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].archivo = urlArchivoFinal;
            }
        } else {
            this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled1 = false;
            this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled2 = true;
            this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled3 = true;

            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                life: 6000,
                detail: 'El archivo cargado no corresponde a una imagen.'
            });
        }
    }

    onRemoveFile(rowIndex) {
        const idArchivo = rowIndex;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].extencion = null;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].archivo = null;

        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled1 = false;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled2 = true;
        this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].botonDisabled3 = true;
    }

    b64toBlob(b64Data, contentType, sliceSize) {
        contentType = contentType || '';
        sliceSize = sliceSize || 512;

        const byteCharacters = atob(b64Data);
        const byteArrays = [];

        for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            const slice = byteCharacters.slice(offset, offset + sliceSize);

            const byteNumbers = new Array(slice.length);
            for (let i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }

            const byteArray = new Uint8Array(byteNumbers);

            byteArrays.push(byteArray);
        }

        const blob = new Blob(byteArrays, {type: contentType});
        return byteArrays;
    }

    showLightbox(idArchivo) {
        const archivo = this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].archivo;
        const tipoArchivo = this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO[idArchivo].tipoArchivoDTO.descripcion;

        if (archivo === undefined || archivo === null) {
            this.imageObject = [
                {
                    image: null,
                    title: 'No se ha cargado ningun archivo',
                }
            ];
        } else {
            this.imageObject = [{
                image: 'data:image/jpeg;base64,' + archivo, // Support base64 image
                title: tipoArchivo, // Optional: You can use this key if want to show image with title
                // alt: 'Image alt' //Optional: You can use this key if want to show image with alt
            }];
        }
        this.displayVistaPrevia = true;
        this.puedeMostrarDialogoImg = true;
        this.selectedImageIndex = 0;
    }

    onCloseDialogoImg() {
        this.imageObject = [{
            image: null,
            title: 'No se ha cargado ningun archivo',
        }];
        this.displayVistaPrevia = false;
        this.puedeMostrarDialogoImg = false;
        this.selectedImageIndex = -1;
    }

    validaFormulario() {
        this.formularioValido = false;
        this.errorForm = false;
        this.errorText = '';
        this.blockUI.start('Validando datos...');

        if ((this.nuevaSolicitudCreditoDTO.nombre === undefined || this.nuevaSolicitudCreditoDTO.nombre === null) || this.nuevaSolicitudCreditoDTO.nombre === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', nombre de afiliado' : 'nombre de afiliado';
        }
        if ((this.nuevaSolicitudCreditoDTO.apellidoPaterno === undefined || this.nuevaSolicitudCreditoDTO.apellidoPaterno === null) || this.nuevaSolicitudCreditoDTO.apellidoPaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido paterno de afiliado' : 'apellido paterno de afiliado';
        }
        if ((this.nuevaSolicitudCreditoDTO.apellidoMaterno === undefined || this.nuevaSolicitudCreditoDTO.apellidoMaterno === null) || this.nuevaSolicitudCreditoDTO.apellidoMaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido materno de afiliado' : 'apellido materno de afiliado';
        }
        if ((this.fechaNacimiento === undefined || this.fechaNacimiento === null)) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', fecha de nacimiento' : 'fecha de nacimiento';
        }
        if ((this.nuevaSolicitudCreditoDTO.telefono === undefined || this.nuevaSolicitudCreditoDTO.telefono === null) || this.nuevaSolicitudCreditoDTO.telefono === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', telefono de afiliado' : 'telefono de afiliado';
        }
        if ((this.nuevaSolicitudCreditoDTO.email === undefined || this.nuevaSolicitudCreditoDTO.email === null) || this.nuevaSolicitudCreditoDTO.email === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', email de afiliado' : 'email de afiliado';
        }
        if ((this.nuevaSolicitudCreditoDTO.empresa === undefined || this.nuevaSolicitudCreditoDTO.empresa === null) || this.nuevaSolicitudCreditoDTO.empresa === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', empresa' : 'empresa';
        }
        if ((this.nuevaSolicitudCreditoDTO.numeroEmpleado === undefined || this.nuevaSolicitudCreditoDTO.numeroEmpleado === null) || this.nuevaSolicitudCreditoDTO.numeroEmpleado === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', numero de empleado' : 'numero de empleado';
        }
        if ((this.fechaAntiguedad === undefined || this.fechaAntiguedad === null)) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', fecha ingreso a la empresa' : 'fecha ingreso a la empresa';
        }
        if ((this.nuevaSolicitudCreditoDTO.salarioMensualNeto === undefined || this.nuevaSolicitudCreditoDTO.salarioMensualNeto === null) || this.nuevaSolicitudCreditoDTO.salarioMensualNeto < 1) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', salario mensual neto' : 'salario mensual neto';
        }
        if ((this.nuevaSolicitudCreditoDTO.salarioMensualBruto === undefined || this.nuevaSolicitudCreditoDTO.salarioMensualBruto === null) || this.nuevaSolicitudCreditoDTO.salarioMensualBruto < 1) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', salario mensual bruto' : 'salario mensual bruto';
        }
        if ((this.montoCreditoSeleccionado === undefined || this.montoCreditoSeleccionado === null)) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', monto de credito' : 'monto de credito';
        }

        const contacto1 = this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[0];
        if ((contacto1.parentesco === undefined || contacto1.email  === null) || contacto1.parentesco === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', parentesco con contacto 1' : 'parentesco con contacto 1';
        }
        if ((contacto1.nombre === undefined || contacto1.email === null) || contacto1.nombre === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', nombre de contacto 1' : 'nombre de contacto 1';
        }
        if ((contacto1.apellidoPaterno === undefined || contacto1.email === null) || contacto1.apellidoPaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido paterno de contacto 1' : 'apellido paterno de contacto 1';
        }
        if ((contacto1.apellidoMaterno === undefined || contacto1.email === null) || contacto1.apellidoMaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido materno de contacto 1' : 'apellido materno de contacto 1';
        }
        if ((contacto1.telefono === undefined || contacto1.email === null) || contacto1.telefono === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', telefono de contacto 1' : 'telefono de contacto 1';
        }
        if ((contacto1.email === undefined || contacto1.email === null) || contacto1.email === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', email de contacto 1' : 'email de contacto 1';
        }

        const contacto2 = this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[1];
        if ((contacto2.parentesco === undefined || contacto2.email === null) || contacto2.parentesco === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', parentesco con contacto 2' : 'parentesco con contacto 2';
        }
        if ((contacto2.nombre === undefined || contacto2.email === null) || contacto2.nombre === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', nombre de contacto 2' : 'nombre de contacto 2';
        }
        if ((contacto2.apellidoPaterno === undefined || contacto2.email === null) || contacto2.apellidoPaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido paterno de contacto 2' : 'apellido paterno de contacto 2';
        }
        if ((contacto2.apellidoMaterno === undefined || contacto2.email === null) || contacto2.apellidoMaterno === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', apellido materno de contacto 2' : 'apellido materno de contacto 2';
        }
        if ((contacto2.telefono === undefined || contacto2.email === null) || contacto2.telefono === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', telefono de contacto 2' : 'telefono de contacto 2';
        }
        if ((contacto2.email === undefined || contacto2.email === null) || contacto2.email === '') {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', email de contacto 2' : 'email de contacto 2';
        }

        if (!this.mailValidoForm) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', validar correos electronicos' : 'validar correos electronicos';
        }

        if (!this.telefonoValidoForm) {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', validar numeros telefonicos' : 'validar numeros telefonicos';
        }

        if (!this.errorForm && (this.mailValidoForm && this.telefonoValidoForm)) {
            this.formularioValido = true;
        }
        this.blockUI.stop();
        return this.formularioValido;
    }

    guardarSolicitudCredito() {
        this.archivosCargados = false;
        if (this.validaFormulario() && this.validarArchivosCargados()) {
            this.blockUI.start('Guardando solicitud de crédito...');
            this.nuevaSolicitudCreditoDTO.idMontoCredito = this.montoCreditoSeleccionado.idMontoCredito;
            this.nuevaSolicitudCreditoDTO.fechaNacimiento = this.fechaNacimiento;
            this.nuevaSolicitudCreditoDTO.fechaIngresoCia = this.fechaAntiguedad;
            this.nuevaSolicitudCreditoDTO.origenSolicitud = 0;

            if (this.nuevaSolicitudCreditoDTO.idCredito === undefined || this.nuevaSolicitudCreditoDTO.idCredito === null) {
                this.applicationInitService.guardarSolicitudCredito(this.nuevaSolicitudCreditoDTO).subscribe(msg => {
                    this.blockUI.stop();
                    if (msg) {
                        this.onRegresarAListado('gSolicitud');
                    } else {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Error',
                            detail: 'Ocurrió un error al guardar la informacion'
                        });
                    }
                }, error => {
                    this.blockUI.stop();
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'Ocurrió un error al guardar la información: ' +
                            error.error.Mensaje
                    });
                });
            } else {
                this.applicationInitService.modificarSolicitudCredito(this.nuevaSolicitudCreditoDTO).subscribe(msg => {
                    this.blockUI.stop();
                    if (msg) {
                        this.onRegresarAListado('gSolicitud');
                    } else {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Error',
                            detail: 'Ocurrió un error al guardar la información'
                        });
                    }
                }, error => {
                    this.blockUI.stop();
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'Ocurrió un error al guardar la información: ' +
                            error.error.Mensaje
                    });
                });
            }
        } else {
            var camposfaltantes = '';
            if (this.errorText.length > 0) {
                camposfaltantes = 'Campos faltantes requeridos: ' + this.errorText;
            }
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Es necesario llenar el formulario y cargar todos los archivos solicitados.' +
                     + (this.errorText.length > 0 ) ? 'Campos faltantes requeridos: ' + this.errorText : ''
            });
        }
    }

    validarMonto() {
        this.disableMonto = true;
        this.catalogoMontoCreditoFiltrado = [];
        let montoSelEnFiltrados = false;

        if (!(this.fechaAntiguedad === undefined || this.fechaAntiguedad === null) &&
            !(this.nuevaSolicitudCreditoDTO.salarioMensualNeto === undefined
                || this.nuevaSolicitudCreditoDTO.salarioMensualNeto === null)) {
            this.calcDate(this.hoy, this.fechaAntiguedad);
            for (const montoCredito of this.catalogoMontoCredito) {
                if (montoCredito.validoDesde <= this.antiguedad[0]) {
                    if (this.nuevaSolicitudCreditoDTO.salarioMensualNeto >= montoCredito.ingresoNeto) {
                        this.catalogoMontoCreditoFiltrado.push(montoCredito);
                    }
                }
            }
            // console.log(this.catalogoMontoCreditoFiltrado);
            if (this.catalogoMontoCreditoFiltrado.length > 0) {
                this.disableMonto = false;
                if (this.nuevaSolicitudCreditoDTO.montoCreditoDTO !== undefined &&
                    this.nuevaSolicitudCreditoDTO.montoCreditoDTO !== null) {
                    for (const montoCredito of this.catalogoMontoCreditoFiltrado) {
                        if (montoCredito.idMontoCredito === this.nuevaSolicitudCreditoDTO.montoCreditoDTO.idMontoCredito) {
                            montoSelEnFiltrados = true;
                            this.nuevaSolicitudCreditoDTO.montoCreditoDTO.montoStr = montoCredito.montoStr;
                        }
                    }
                }
            }

            if (montoSelEnFiltrados === false) {
                this.nuevaSolicitudCreditoDTO.montoCreditoDTO = null;
            }
        }
    }

    validarArchivosCargados() {
        this.archivosCargados = false;
        let archivosCargados = 0;
        for (const archivoCreditoDTO of this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO) {
            if (archivoCreditoDTO.archivo !== undefined && archivoCreditoDTO.archivo !== null) {
                archivosCargados++;
            }
        }

        if (this.nuevaSolicitudCreditoDTO.listaArchivoCreditoDTO.length === archivosCargados) {
            this.archivosCargados = true;
        } else {
            this.errorForm = true;
            this.errorText += (this.errorText.length > 0) ? ', faltan archivos por cargar' : 'faltan archivos por cargar';
        }
        return this.archivosCargados;
    }

    calcDate(hoy: Date, fecha: Date) {
        const dia = fecha.getDate();
        const mes = fecha.getMonth();
        const anio = fecha.getFullYear();

        // cogemos los valores actuales
        const ahoraAnio = hoy.getFullYear();
        const ahoraMes = hoy.getMonth();
        const ahoraDia = hoy.getDate();

        // realizamos el calculo
        let anios = (ahoraAnio + 1900) - anio;
        if (ahoraMes < mes) {
            anios--;
        }
        if ((mes === ahoraMes) && (ahoraDia < dia)) {
            anios--;
        }
        if (anios > 1900) {
            anios -= 1900;
        }
        if (anios === 1900) {
            anios -= 1900;
        }

        // calculamos los meses
        let meses = 0;

        if (ahoraMes > mes && dia > ahoraDia) {
            meses = ahoraMes - mes - 1;
        } else if (ahoraMes > mes) {
            meses = ahoraMes - mes;
        }

        if (ahoraMes < mes && dia < ahoraDia) {
            meses = 12 - (mes - ahoraMes);
        } else if (ahoraMes < mes) {
            meses = 12 - (mes - ahoraMes + 1);
        }

        if (ahoraMes === mes && dia > ahoraDia) {
            meses = 11;
        }

        // calculamos los dias
        let dias = 0;
        if (ahoraDia > dia) {
            dias = ahoraDia - dia;
        }
        if (ahoraDia < dia) {
            const ultimoDiaMes = new Date(ahoraAnio, ahoraMes - 1, 0);
            dias = ultimoDiaMes.getDate() - (dia - ahoraDia);
        }
        // console.log(edad + ' años, ' + meses + ' meses y ' + dias + ' días');

        this.antiguedad = [
            anios,
            meses,
            dias
        ];
    }

    onAbreCambiaEstatus() {
        this.displayCambioEstatus = true;
        this.puedeMostrarDialogo = true;

        this.nuevaObservacionCreditoDTO = new ObservacionCreditoDTO();
        this.observacion = '';
        this.estatusCreditoSeleccionado = null;
    }

    onCloseDialogCambiaEstatus(respuesta: string) {
        switch (respuesta) {
            case 'SI': {
                this.guardarObservaciones();
                break;
            }
            case 'NO': {
                // this.checkAceptaTerminos = false;
                break;
            }
        }
        this.displayCambioEstatus = false;
        this.puedeMostrarDialogo = false;
    }

    guardarObservaciones() {
        this.blockUI.start('Guardando observación...');
        this.nuevaObservacionCreditoDTO.idEstatusCredito = this.estatusCreditoSeleccionado.idEstatusCredito;
        this.nuevaObservacionCreditoDTO.idCredito = this.nuevaSolicitudCreditoDTO.idCredito;
        this.nuevaObservacionCreditoDTO.observacion = this.observacion;
        this.applicationInitService.guardarObservacionCredito(this.nuevaObservacionCreditoDTO).subscribe(msg => {
            this.blockUI.stop();
            if (msg) {
                this.onRegresarAListado('gObservacion');
            } else {
                this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error al guardar la informacion'});
            }
        }, error => {
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
            this.blockUI.stop();
            this.messageService.add({severity: 'error', summary: 'Error', detail: 'Ocurrió un error al guardar la informacion: ' +
                    mensajesErrorM});
        });
    }

    esEmailValido(origen: string, id: number) {
        let email = '';
        if (origen === 'correoAfil') {
            email = this.nuevaSolicitudCreditoDTO.email;
        } else if (origen === 'correoContacto') {
            email = this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[id].email;
        }

        let mailValido = false;
        // 'use strict';

        const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

        if (email.match(EMAIL_REGEX)) {
            mailValido = true;
        }
        // return mailValido;
        if (origen === 'correoAfil') {
            this.nuevaSolicitudCreditoDTO.correoAfilNoValido = mailValido;
        } else if (origen === 'correoContacto') {
            this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[id].correoContactoNoValido = mailValido;
        }

        if (this.nuevaSolicitudCreditoDTO.correoAfilNoValido) {
            if (this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[0].correoContactoNoValido) {
                if (this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[1].correoContactoNoValido) {
                    this.mailValidoForm = true;
                } else {
                    this.mailValidoForm = false;
                }
            } else {
                this.mailValidoForm = false;
            }
        } else {
            this.mailValidoForm = false;
        }
    }

    esTelefonoValido(origen, id) {
        let telefono = '';
        if (origen === 'telefonoAfil') {
            telefono = this.nuevaSolicitudCreditoDTO.telefono;
        } else if (origen === 'telefonoContacto') {
            telefono = this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[id].telefono;
        }

        let telefonoValido = false;
        // const TELNUMBER_REGEX = /^([0-9]{3}\) [0-9]{3}[ -][0-9]{4}/;
        // const TELNUMBER_REGEX = /^(\([0-9]{3}\)) [0-9]{3}[ -][0-9]{4}/;
        const TELNUMBER_REGEX = /^[0-9]{10}/;
        if (telefono.match(TELNUMBER_REGEX)) {
            telefonoValido = true;
        }

        if (origen === 'telefonoAfil') {
            this.nuevaSolicitudCreditoDTO.telefonoAfilNoValido = telefonoValido;
        } else if (origen === 'telefonoContacto') {
            this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[id].telefonoContactoNoValido = telefonoValido;
        }

        if (this.nuevaSolicitudCreditoDTO.telefonoAfilNoValido) {
            if (this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[0].telefonoContactoNoValido) {
                if (this.nuevaSolicitudCreditoDTO.listaContactoCreditoDTO[1].telefonoContactoNoValido) {
                    this.telefonoValidoForm = true;
                } else {
                    this.telefonoValidoForm = false;
                }
            } else {
                this.telefonoValidoForm = false;
            }
        } else {
            this.telefonoValidoForm = false;
        }
    }

    mostrarMensajesLista(origen: string) {
        if (origen === 'gSolicitud') {
            setTimeout(() => {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Registro exitoso',
                    life: 6000,
                    detail: 'La solicitud se registró satisfactoriamente.'
                });
            }, 2000);
        } else if (origen === 'gObservacion') {
            setTimeout(() => {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Registro exitoso',
                    life: 6000,
                    detail: 'Se generó la observación satisfactoriamente.'
                });
            }, 2000);
        } else if (origen === 'mEspera') {
            this.messageService.add({
                severity: 'info',
                summary: 'Espera',
                life: 6000,
                detail: this.tooltipBtnNueva
            });
        }
    }

    validarDialogCambiaEstatus() {
        // this.nuevaObservacionCreditoDTO.
        this.formuObservacionValido = false;
        if (this.estatusCreditoSeleccionado !== undefined && this.estatusCreditoSeleccionado !== null) {
            if (this.observacion !== undefined && this.observacion !== null && this.observacion !== '') {
                this.formuObservacionValido = true;
            }
        }
    }

    formatNumber(num, decimales: boolean) {
        if (!num || num === 'NaN') {
            return '';
        }
        if (num === 'Infinity') {
            return '&#x221e;';
        }
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num)) {
            num = '0';
        }
        const sign = (num === (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        const cents = num % 100;
        num = Math.floor(num / 100).toString();
        let centsStr = '';
        if (cents < 10) {
            centsStr = '0' + cents;
        }
        for (let i = 0; i < Math.floor((num.length - (1 + i)) / 3) ; i++) {
            num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
        }
        // return (((sign) ? '' : '-') + num + ',' + centsStr);
        if (decimales) {
            return (num + '.' + centsStr);
        } else {
            return (num);
        }
    }
}
