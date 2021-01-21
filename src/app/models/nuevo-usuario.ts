export class NuevoUsuario {
    nombres: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    nick: string;
    email: string;
    roles: string[];
    passw: string;

    constructor(nombres: string, apellidoPaterno: string, apellidoMaterno: string, nick: string, email: string,
                roles: string[], passw: string) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nick = nick;
        this.email = email;
        this.roles = roles;
        this.passw = passw;
    }
}
