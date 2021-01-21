import {EstatusCreditoDTO} from './estatus-credito-dto';

export class ObservacionCreditoDTO {
    idObservacionCredito: number;
    idCredito: number;
    idUsuario: number;
    arqIdUsuario: number;
    idEstatusCredito: number;
    estatusCredito: EstatusCreditoDTO;
    observacion: string;

    constructor() {
    }
}
