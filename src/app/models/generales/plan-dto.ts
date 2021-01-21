import {ColorPlanDTO} from './color-plan-dto';

export class PlanDTO {
    idPlan: number;
    nombre: string;
    monto: number;
    descripcion: string;
    moneda: string;
    duracion: number;
    unidadDuracion: string;
    tiempoPrueba: number;
    listaColorPlanDTO: ColorPlanDTO[] = [];

    constructor() {
    }
}
