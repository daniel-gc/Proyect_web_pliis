import {ContactoCreditoDTO} from './contacto-credito-dto';
import {ArchivoCreditoDTO} from './archivo-credito-dto';
import {EstatusCreditoDTO} from './estatus-credito-dto';
import {MontoCreditoDTO} from './monto-credito-dto';
import {ObservacionCreditoDTO} from './observacion-credito-dto';

export class NuevaSolicitudCreditoDTO {
    idCredito: number;
    idUsuario: number;
    esAfiliado: boolean;
    esMiembro: boolean;
    nombre: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    fechaNacimiento: Date;
    telefono: string;
    email: string;
    empresa: string;
    numeroEmpleado: string;
    fechaIngresoCia: Date;
    salarioMensualNeto: number;
    salarioMensualBruto: number;
    idMontoCredito: number;
    montoCreditoDTO: MontoCreditoDTO;
    idEstatusCredito: number;
    estatusCreditoDTO: EstatusCreditoDTO;
    fechaSolicitud: Date;
    fechaConclucion: Date;
    origenSolicitud: number;
    listaContactoCreditoDTO: ContactoCreditoDTO[] = [];
    listaArchivoCreditoDTO: ArchivoCreditoDTO[] = [];
    listaObservacionCreditoDTO: ObservacionCreditoDTO[] = [];
    correoAfilNoValido: boolean;
    telefonoAfilNoValido: boolean;

    constructor() {
    }
}
