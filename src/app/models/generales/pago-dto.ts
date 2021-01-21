export class PagoDTO {
    idPlan: number;
    montoPlan: number;
    descripcionPlan: string;
    monedaPlan: string;
    token: string;
    device: string;

    idPago: number;
    idUsuario: number;
    idPagoOpenpay: string;
    description: string;
    monto: number;
    status: string;
    fecha: Date;
    tipo: string;
    authorization: string;
    errorCode: number;
    errorMessage: string;

    constructor() {
    }
}
