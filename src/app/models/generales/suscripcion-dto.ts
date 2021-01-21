import {PlanDTO} from './plan-dto';
import {PagoDTO} from './pago-dto';
import {MiembroDTO} from './miembro-dto';

export class SuscripcionDTO {
    planDTO: PlanDTO;
    miembroDTO: MiembroDTO;
    pagoDTO: PagoDTO;
    fechaInicio: Date;
    fechaFin: Date;
    activo: boolean;

    constructor() {
    }
}
