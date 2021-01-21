export class MesDTO {
    idMes: number;
    nombre: string;
    codigo: string;
    constructor(idMes, nombre, codigo) {
        this.idMes = idMes;
        this.nombre = nombre;
        this.codigo = codigo;
    }
}
