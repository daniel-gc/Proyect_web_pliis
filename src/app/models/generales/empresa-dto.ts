import {CentroTrabajoDTO} from './centro-trabajo-dto';

export class EmpresaDTO {
    idEmpresa: number;
    nombre: string;
    direccion: string;
    emailContacto: string;
    emailRh: string;
    centrosTrabajo: CentroTrabajoDTO[];

    constructor() {
    }
}
