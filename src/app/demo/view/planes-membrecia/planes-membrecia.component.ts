import {Component, forwardRef, OnInit} from '@angular/core';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/api';
import {NG_VALUE_ACCESSOR} from '@angular/forms';
import {BreadcrumbService} from '../../../breadcrumb.service';
import {ApplicationInitService} from '../../../services/application-init.service';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {PlanDTO} from '../../../models/generales/plan-dto';
import {MesDTO} from '../../../models/generales/mes-dto';
import {TarjetaDTO} from '../../../models/generales/tarjeta-dto';
import {PagoDTO} from '../../../models/generales/pago-dto';
import { OpenpayComponent } from '../openpay/openpay.component';
import {Router} from '@angular/router';
import {TokenService} from '../../../services/security/token.service';
import {SuscripcionDTO} from '../../../models/generales/suscripcion-dto';

@Component({
    selector: 'app-planes-membrecia',
    templateUrl: './planes-membrecia.component.html',
    styleUrls: ['./planes-membrecia.component.css'],
    providers: [MessageService,
        ConfirmationService,
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => PlanesMembreciaComponent),
            multi: true,
        }, OpenpayComponent]
})
export class PlanesMembreciaComponent implements OnInit {
    constructor(
        private breadcrumbService: BreadcrumbService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private applicationInitService: ApplicationInitService,
        private openPayComponent: OpenpayComponent,
        private router: Router,
        private tokenService: TokenService) {
        this.breadcrumbService.setItems([
            {label: 'Seleccion de plan de membrecía', routerLink: ['/planesMembrecia']}
        ]);
    }

    @BlockUI() blockUI: NgBlockUI;
    // formTarjeta: FormGroup;
    catalogoPlan: PlanDTO[] = [];
    planSeleccionado: PlanDTO;
    mostrarPlanes: boolean;
    mostrarFormTarjeta: boolean;
    voltearTarjeta: boolean;
    voltearBoton: boolean;
    mostrarFormularioT: boolean;
    mostrarConfirmaPago: boolean;
    mostrarBotonPago: boolean;
    mesesTarjeta: object[];
    mesSeleccionado: number;
    aniosTarjeta: number[] = [];
    anioSeleccionado: number;
    datosTarjeta: TarjetaDTO = null;
    terminosOpenpay: string;
    checkAceptaTerminos: boolean;
    pagoDTO: PagoDTO;
    display = false;
    displayTerminosCondiciones = false;
    puedeMostrarDialogo = false;
    mostrarBotonSiNo = true;
    suscripcionActiva: SuscripcionDTO;

    ngOnInit() {
        this.mostrarPlanes = true;
        this.mostrarFormTarjeta = false;
        this.validaSuscripcionActiva();
    }

    closeDialog() {
        this.display = false;
    }

    validaSuscripcionActiva() {
        this.blockUI.start('Cargando...');
        this.applicationInitService.validaSuscripcionActiva().subscribe(data0 => {
            this.suscripcionActiva = data0;
            this.blockUI.stop();
            if (this.suscripcionActiva !== null) {
                if(this.suscripcionActiva.planDTO !== null) {
                    this.confirmationService.confirm({
                        message: 'Usted cuenta actualmente con una suscripcion activa hasta' + this.suscripcionActiva.fechaFin +
                             '¿Desea continuar y seleccionar un nuevo plan ? \n',
                        header: 'Suscripcion activa',
                        icon: 'pi pi-exclamation-triangle',
                        accept: () => {
                            this.consultarPlanes();
                        },
                        reject: () => {
                            this.router.navigate(['']);
                        }
                    });
                } else {
                    this.consultarPlanes();
                }
            } else {
                this.consultarPlanes();
            }
        }, error => {
            this.blockUI.stop();
            this.catalogoPlan = [];
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
    }

    consultarPlanes() {
        this.blockUI.start('Cargando...');
        this.applicationInitService.getCatalogoPlanes().subscribe(data0 => {
            this.catalogoPlan = data0;
            this.blockUI.stop();
        }, error => {
            this.blockUI.stop();
            this.catalogoPlan = [];
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Ocurrió un error en la búsqueda de datos requeridos.' +
                    ' Vuelva a cargar la página, si el problema persiste, contacte a su administrador'});
        });
    }

    llenarMeses() {
        const mesDTO01: MesDTO = new MesDTO(1, 'Enero', '01');
        const mesDTO02: MesDTO = new MesDTO(2, 'Febrero', '02');
        const mesDTO03: MesDTO = new MesDTO(3, 'Marzo', '03');
        const mesDTO04: MesDTO = new MesDTO(4, 'Abril', '04');
        const mesDTO05: MesDTO = new MesDTO(5, 'Mayo', '05');
        const mesDTO06: MesDTO = new MesDTO(6, 'Junio', '06');
        const mesDTO07: MesDTO = new MesDTO(7, 'Julio', '07');
        const mesDTO08: MesDTO = new MesDTO(8, 'Agosto', '08');
        const mesDTO09: MesDTO = new MesDTO(9, 'Septiembre', '09');
        const mesDTO10: MesDTO = new MesDTO(10, 'Octuble', '10');
        const mesDTO11: MesDTO = new MesDTO(11, 'Noviembre', '11');
        const mesDTO12: MesDTO = new MesDTO(12, 'Diciembre', '12');

        this.mesesTarjeta = [
            {idMes: 1, nombre: 'Enero', codigo: '01'},
            {idMes: 2, nombre: 'Febrero', codigo: '02'},
            {idMes: 3, nombre: 'Marzo', codigo: '03'},
            {idMes: 4, nombre: 'Abril', codigo: '04'},
            {idMes: 5, nombre: 'Mayo', codigo: '05'},
            {idMes: 6, nombre: 'Junio', codigo: '06'},
            {idMes: 7, nombre: 'Julio', codigo: '07'},
            {idMes: 8, nombre: 'Agosto', codigo: '08'},
            {idMes: 9, nombre: 'Septiembre', codigo: '09'},
            {idMes: 10, nombre: 'Octuble', codigo: '10'},
            {idMes: 11, nombre: 'Noviembre', codigo: '11'},
            {idMes: 12, nombre: 'Diciembre', codigo: '12'}
        ];
    }

    llenarAnios() {
        const anioActual: number = new Date().getFullYear();
        for (let i = 0; i <= 8; i++) {
            this.aniosTarjeta.push(anioActual + i);
        }
    }

    onSelectPlan(plan: PlanDTO) {
        this.planSeleccionado = plan;
        this.confirmationService.confirm({
           message: '¿Desea seleccionar el plan ' + plan.nombre + '? \n' +
               plan.nombre + ' - ' + plan.descripcion,
            header: 'Confirmación de plan seleccionado',
            icon: 'pi pi-exclamation-triangle',
           accept: () => {
                this.mostrarPlanes = false;
                this.mostrarFormTarjeta = true;
                this.voltearTarjeta = false;
                this.voltearBoton = true;
                this.mostrarFormularioT = true;
                this.llenarMeses();
                this.llenarAnios();
                this.anioSeleccionado = -1;
                this.mesSeleccionado = -1;
                this.datosTarjeta = new TarjetaDTO();
           }
        });
    }

    onRegresaAPlanes() {
        this.mostrarPlanes = true;
        this.mostrarFormTarjeta = false;
    }

    onClickTarjeta(accion: string, dinamico: string) {
        switch (accion) {
            case 'tarjeta': {
                if (dinamico === 'S') {
                    this.voltearTarjeta = !this.voltearTarjeta;
                } else if (dinamico === 'D') {
                    this.voltearTarjeta = false;
                } else if (dinamico === 'T') {
                    this.voltearTarjeta = true;
                }
                break;
            }
            case 'btn': {
                this.voltearBoton = !this.voltearBoton;
                this.mostrarFormularioT = !this.mostrarFormularioT;
                break;
            }
            case 'formulario': {
                this.mostrarFormularioT = !this.mostrarFormularioT;
                break;
            }
        }
    }

    validarNumTarjeta() {
        let numeroTarjeta = this.datosTarjeta.numeroTarjeta;
        numeroTarjeta = numeroTarjeta
            .replace(/\s/g, '')
            .replace(/\D/g, '')
            .replace(/([0-9]{4})/g, '$1 ')
            .trim();
        this.datosTarjeta.numeroTarjeta = numeroTarjeta;

        if (numeroTarjeta.substr(0, 1) === '4') {
            // Tarjeta Visa
            this.datosTarjeta.srcImagen = './assets/layout/images/pagos_tarjeta/logos/visa.png';
        } else if (numeroTarjeta.substr(0, 1) === '5') {
            // Tarjeta Mastercard
            this.datosTarjeta.srcImagen = './assets/layout/images/pagos_tarjeta/logos/mastercard.png';
        } else {
            // Tarjeta otro banco
            this.datosTarjeta.srcImagen = './assets/layout/images/pagos_tarjeta/logos/logo_vacio.png';
        }

        this.onClickTarjeta('tarjeta', 'D');
    }

    validarNombre() {
        let nombreTarjeta = this.datosTarjeta.nombreTarjeta;

        nombreTarjeta = nombreTarjeta
            .replace(/[0-9]/g, '');

        this.datosTarjeta.nombreTarjeta = nombreTarjeta;
        this.onClickTarjeta('tarjeta', 'D');
    }

    validaMesAnio(combo: string, value: any) {
        switch (combo) {
            case 'mes': {
                // this.datosTarjeta.mesExpira = this.mesSeleccionado;
                // tslint:disable-next-line:prefer-for-of
                for (let i = 0; i < this.mesesTarjeta.length; i++) {
                    const mes = this.mesesTarjeta[i];
                    // @ts-ignore
                    if (mes.idMes.toString() === this.mesSeleccionado) {
                        // @ts-ignore
                        this.datosTarjeta.mesExpira = new MesDTO(mes.idMes, mes.nombre, mes.codigo);
                    }
                }
                break;
            }
            case 'anio': {
                this.datosTarjeta.anioCorto = this.anioSeleccionado.toString().slice(2);
                this.datosTarjeta.anioExpira = this.anioSeleccionado;
                break;
            }
        }
        this.onClickTarjeta('tarjeta', 'D');
    }

    validarCcv() {
        let ccv = this.datosTarjeta.ccv.toString();

        ccv = ccv
            .replace(/\s/g, '')
            .replace(/\D/g, '')
            .trim();

        this.datosTarjeta.ccv = ccv;
        this.onClickTarjeta('tarjeta', 'T');
    }

    onConfirmaPago() {
        let tarjetaValida = true;
        let nombreValido = true;
        let expansionValido = true;
        let codigoCcvValido = true;

        if (this.datosTarjeta.nombreTarjeta.trim().length === 0) {
            nombreValido = false;
        }
        tarjetaValida = this.openPayComponent.getOpenPay().card.validateCardNumber(this.datosTarjeta.numeroTarjeta);
        let codeMonth = '-1';
        if (this.datosTarjeta.mesExpira !== null) {
            codeMonth = this.datosTarjeta.mesExpira.codigo;
        }
        expansionValido = this.openPayComponent.getOpenPay().card.validateExpiry(
            codeMonth, this.datosTarjeta.anioExpira
        );
        codigoCcvValido = this.openPayComponent.getOpenPay().card.validateCVC(
            this.datosTarjeta.ccv
        );
        /*if ((this.datosTarjeta.numeroTarjeta.length === 0) || (this.datosTarjeta.numeroTarjeta.length < 19)) {
             tarjetaValida = false;
        }*/
        /*if ((this.datosTarjeta.mesExpira === null) ||
            (this.datosTarjeta.anioExpira === -1)) {
            expansionValido = false;
        }*/
        /*if ((this.datosTarjeta.ccv.length === 0) ||
            (this.datosTarjeta.ccv.length < 3)) {
            codigoCcvValido = false;
        }*/

        if (tarjetaValida && nombreValido && expansionValido && codigoCcvValido) {
            this.mostrarPlanes = false;
            this.mostrarFormTarjeta = false;
            this.mostrarConfirmaPago = true;
            this.mostrarBotonPago = true;
        } else {
            this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: 'Favor de validar sus datos y llenar todos los campos requeridos.'
            });
        }
    }

    onClickTerminosCondiciones() {
        this.terminosOpenpay = 'PLIIS no recibe directamente del cliente el pago por servicios o productos. ' +
            'Para formalizar su compra, utilizamos OpenPay ®, por lo cual las condiciones contractuales, ' +
            'transaccionales y de seguridad informática deberá consultarlas y apegarse a las mismas, en: ' +
            'https://www.openpay.mx/en/terminos-servicio.html.';
        this.checkAceptaTerminos = false;
        this.displayTerminosCondiciones = true;
        this.puedeMostrarDialogo = true;
    }

    onClickOpenOpenpay() {
        window.open('https://www.openpay.mx/en/terminos-servicio.html', "_blank");
        // const url = 'https://www.openpay.mx/en/terminos-servicio.html';
        // const win = window.open(url, '_blank');
        // win.opener = null;
        // win.focus();
    }

    onCloseDialogTerminosCondiciones(respuesta: string) {
        switch (respuesta) {
            case 'SI': {
                this.checkAceptaTerminos = true;
                break;
            }
            case 'NO': {
                this.checkAceptaTerminos = false;
                break;
            }
        }
        this.displayTerminosCondiciones = false;
        this.puedeMostrarDialogo = false;
    }

    onRegresaModificaDatos() {
        this.mostrarPlanes = false;
        this.mostrarFormTarjeta = true;
        this.mostrarConfirmaPago = false;
    }

    onRealizaPago() {
        this.blockUI.start('Cargando...');
        if (this.checkAceptaTerminos) {
            this.pagoDTO = new PagoDTO();
            this.pagoDTO.idPlan = this.planSeleccionado.idPlan;
            this.pagoDTO.montoPlan = this.planSeleccionado.monto;
            this.pagoDTO.monedaPlan = this.planSeleccionado.moneda;
            this.pagoDTO.descripcionPlan = this.planSeleccionado.descripcion;

            const cardNumber = this.datosTarjeta.numeroTarjeta.replace(/\s/g, '');
            this.openPayComponent.getOpenPay().token.create({
                card_number: cardNumber,
                holder_name: this.datosTarjeta.nombreTarjeta,
                expiration_year: this.datosTarjeta.anioCorto,
                expiration_month: this.datosTarjeta.mesExpira.codigo,
                cvv2: this.datosTarjeta.ccv
            }, (token) => {
                console.log(token);
                this.pagoDTO.token = token.data.id;
                this.pagoDTO.device = this.openPayComponent.getOpenPay().deviceData.setup();
                this.applicationInitService.realizaPagoMembrecia(this.pagoDTO).subscribe(msg => {
                    this.blockUI.stop();
                    if (msg !== false) {
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Operación exitosa',
                            detail: 'El pago se ha realizado satisfactoriamente.'
                        });
                        this.onLogout();
                        this.mostrarBotonPago = false;
                    } else {
                        this.mostrarBotonPago = true;
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Error',
                            detail: 'Ocurrió un error al realizar el pago'
                        });
                    }
                }, error => {
                    this.blockUI.stop();
                    this.mostrarBotonPago = true;
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'Ocurrió un error al realizar el pago: ' +
                            error.error.Mensaje
                    });
                });
            }, (error) => {
                this.blockUI.stop();
                console.log(error);
                this.mostrarBotonPago = true;
                this.messageService.add({
                    severity: 'error',
                    summary: 'Error',
                    detail: 'Ocurrió un error al realizar el pago, intentelo de nuevo: ' +
                        error.data.description + 'Identificador de solicitud ' + error.data.request_id
                });
            });
        }
    }

    onLogout() {
        this.mostrarBotonSiNo = false;
        this.confirmationService.confirm({
            message: 'El pago se ha realizado satisfactoriamente. Es necesario iniciar sesión nuevamente para reflejar los cambios.',
            header: 'Salir de la plataforma.',
            accept: () => {
                this.tokenService.logOut();
                this.router.navigate(['home']);
                this.router.navigate(['login']);
            }
        });
    }
}
