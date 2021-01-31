import {Injectable, OnInit} from '@angular/core';
import {EmpresaDTO} from '../models/generales/empresa-dto';
import {HttpClient} from '@angular/common/http';
import {LoginUsuario} from '../models/login-usuario';
import {Observable} from 'rxjs';
import {JwtModel} from '../models/jwt-model';
import {ServicioRestDTO} from '../models/generales/servicio-rest-dto';
import {SexoDTO} from '../models/generales/sexo-dto';
import {NacionalidadDTO} from '../models/generales/nacionalidad-dto';
import {EstadoCivilDTO} from '../models/generales/estado-civil-dto';
import {EmpleadoDTO} from '../models/generales/empleado-dto';
import {MensajeDTO} from '../models/generales/mensaje-dto';
import {NuevoUsuarioArquitecturaDTO} from '../models/generales/nuevo-usuario-arquitectura-dto';
import {CambioPasswDTO} from '../models/generales/cambio-passw-dto';
import {NuevoFamiliarDTO} from '../models/generales/nuevo-familiar-dto';
import {AfiliadoParaFamiliarDTO} from '../models/generales/afiliado-para-familiar-dto';
import {RelacionFamiliarDTO} from '../models/generales/relacion-familiar-dto';
import {OlvidePasswDTO} from '../models/generales/olvide-passw-dto';
import {FamiliarDTO} from '../models/generales/familiar-dto';
import {NuevoMiembroArquitecturaDTO} from '../models/generales/nuevo-miembro-arquitectura-dto';
import {PlanDTO} from '../models/generales/plan-dto';
import {PagoDTO} from '../models/generales/pago-dto';
import {SuscripcionDTO} from '../models/generales/suscripcion-dto';
import {FiniquitoDetalleDTO} from '../models/generales/detalle-Finiquito-dto';
import {FiniquitoCalculoDTO} from '../models/generales/calculo-finiquito-dto';
import {BoletoCineDTO} from '../models/generales/boleto-cine-dto';
import {MontoCreditoDTO} from '../models/generales/monto-credito-dto';
import {TipoArchivoDTO} from '../models/generales/tipo-archivo-dto';
import {EstatusCreditoDTO} from '../models/generales/estatus-credito-dto';
import {NuevaSolicitudCreditoDTO} from '../models/generales/nueva-solicitud-credito-dto';
import {ObservacionCreditoDTO} from '../models/generales/observacion-credito-dto';
import { FamiliarBeneficiarioDto } from '../models/familiar_beneficiario/familiar-beneficiario-dto';
// const uriExisteEmpleado = '/afiliacion/existeEmpleado';
// const uriCatalogoSexo = '/afiliacion/catalogos/sexos';
// const uriCatalogoEmpresas = '/afiliacion/catalogos/empresas';
// const uriCatalogoNacionalidad = '/afiliacion/catalogos/nacionalidades';
// const uriCatalogoEstadoCivil = '/afiliacion/catalogos/estadosCiviles';
// const uriCatalogoCentroTrabajo = '/afiliacion/catalogos/centrosTrabajo';
// const uriNuevoUsuario = '/auth/nuevo';
// const uriCarpetaSindical = '/empresas_sindicatos/carpetaSindical';
@Injectable({
    providedIn: 'root'
})
export class ApplicationInitService {
    listaServicioRest: ServicioRestDTO[] = [];

    // Inicio. Declaraciones de los códigos de arquitectura
    //public uriPLIIS = 'https://pliis.mx/PLIIS';
    public uriPLIIS = 'http://localhost:81/PLIIS'; // 3 .130.112.216
    // public uriPLIIS = 'http://3.130.112.216:8080/PLIIS';

    public uriExisteEmpleado = '/afiliacion/existeEmpleado';
    public uriCatalogoSexo = '/afiliacion/catalogos/sexos';
    public uriCatalogoEmpresas = '/afiliacion/catalogos/empresas';
    public uriCatalogoNacionalidad = '/afiliacion/catalogos/nacionalidades';
    public uriCatalogoEstadoCivil = '/afiliacion/catalogos/estadosCiviles';
    public uriCatalogoCentroTrabajo = '/afiliacion/catalogos/centrosTrabajo';
    public uriNuevoUsuario = '/auth/nuevo';
    public uriCarpetaSindical = '/empresas_sindicatos/carpetaSindical/';
    public uriCrearPanico = '/comunicaciones/panico/crear';
    public uriCambiarContrasena = '/general/cambiar_passw';
    public uriNuevoFamiliar = '/afiliacion/familiar/nuevo';
    public uriAfiliadoIdAfiliado = '/afiliacion/afiliadoIdAfiliado';
    public uriCatalogoRelacionesFamiliares = '/afiliacion/catalogos/relacionesFamiliares';
    public uriRecuperaPasswOlvidada = '/general/recupera_passw_olvidada';
    public uriCambiaPasswOlvidada = '/general/cambia_passw_olvidada';
    public uriTodosMisFamiliares = '/afiliacion/familiar/todos';
    public uriCancelaFamiliar = '/afiliacion/familiar/cancelar';
    public uriImportaDatos = '/empresas_sindicatos/empresas/importaDatos';
    public uriNuevoMiembro = '/auth/nuevoMiembro';
    public uriCatalogoPlanes = '/afiliacion/catalogos/planes';
    public uriRealizarPagoMembrecia = '/afiliacion/miembro/pagoMembrecia';
    public uriHistorialPagos = '/afiliacion/miembro/getHistorialPagos';
    public uriCalcularFiniquito = '/afiliacion/calcularFiniquito';
    public uriValidaSuscripcionActiva = '/afiliacion/miembro/validarSuscripcionActiva';
    public uriSolicitaBoletoCine = '/afiliacion/solicitarBoletoCine';
    public uriEnviaEmailBoletoCine = '/afiliacion/enviaEmailBoletoCine';
    public uriCatalogoMontosCredito = '/afiliacion/catalogos/montosCredito';
    public uriCatalogoTipoArchivo = '/afiliacion/catalogos/tipoArchivo';
    public uriCatalogoEstatusCredito = '/afiliacion/catalogos/estatusCredito';
    public uriConsultaSolicitudesCredito = '/afiliacion/credito/consultaSolicitudesCredito';
    public uriNuevaObservacionCredito = '/afiliacion/credito/agregarObservacionCredito';
    public uriNuevoSolicitudCredito = '/afiliacion/credito/generarSolicitudCredito';
    public uriModificaSolicitudCredito = '/afiliacion/credito/modificarSolicitudCredito';
    public uriFamiliarBeneficiarioRegistro = '/empresas_sindicatos/familiarbeneficiario/registro';
    public uriFamiliarBeneficiarioConsulta = '/empresas_sindicatos/familiarbeneficiario/todos';
    public uriFamiliarBeneficiarioModificar = '/empresas_sindicatos/familiarbeneficiario/modificar';
    public uriFamiliarBeneficiarioEliminar = '/empresas_sindicatos/familiarbeneficiario/eliminar';
    // Fin. Declaraciones de los códigos de arquitectura

    constructor(private httpClient: HttpClient) {
    }

    public getCatalogoEmpresa(): Observable<EmpresaDTO[]> {
        return this.httpClient.get<EmpresaDTO[]>(this.uriPLIIS + this.uriCatalogoEmpresas);
    }

    public getCatalogoSexo(): Observable<SexoDTO[]> {
        return this.httpClient.get<SexoDTO[]>(this.uriPLIIS + this.uriCatalogoSexo);
    }

    public getCatalogoNacionalidad(): Observable<NacionalidadDTO[]> {
        return this.httpClient.get<NacionalidadDTO[]>(this.uriPLIIS + this.uriCatalogoNacionalidad);
    }

    public getCatalogoEstadoCivil(): Observable<EstadoCivilDTO[]> {
        return this.httpClient.get<EstadoCivilDTO[]>(this.uriPLIIS + this.uriCatalogoEstadoCivil);
    }

    public getExisteEmpleado(idEmpresa: number, numeroEmpleado: string): Observable<any> {
        return this.httpClient.get<any>(this.uriPLIIS + this.uriExisteEmpleado
            + `/?idEmpresa=${idEmpresa}&numeroEmpleado=${numeroEmpleado}`);
    }

    public getCatalogoCentroTrabajo(idEmpresa: number): Observable<any> {
        return this.httpClient.get<any>(this.uriPLIIS + this.uriCatalogoCentroTrabajo
            + `/?idEmpresa=${idEmpresa}`);
    }

    public guardarNuevoAfiliado(nuevoUsuarioArquitecturaDTO: NuevoUsuarioArquitecturaDTO): Observable<MensajeDTO> {
        return this.httpClient.post<MensajeDTO>(this.uriPLIIS + this.uriNuevoUsuario, nuevoUsuarioArquitecturaDTO);
    }

    public guardarNuevoMiembro(nuevoMiembroArquitecturaDTO: NuevoMiembroArquitecturaDTO): Observable<MensajeDTO> {
        return this.httpClient.post<MensajeDTO>(this.uriPLIIS + this.uriNuevoMiembro, nuevoMiembroArquitecturaDTO);
    }

    public cambiarContrasena(cambioPasswDTO: CambioPasswDTO): Observable<boolean> {
        return this.httpClient.put<boolean>(this.uriPLIIS + this.uriCambiarContrasena, cambioPasswDTO);
    }

    public nuevoFamiliar(familiarDTO: NuevoFamiliarDTO): Observable<boolean> {
        return this.httpClient.post<boolean>(this.uriPLIIS + this.uriNuevoFamiliar, familiarDTO);
    }

    public getAfiliadoIdAfiliado(idAfiliado: number): Observable<AfiliadoParaFamiliarDTO> {
        return this.httpClient.get<AfiliadoParaFamiliarDTO>(this.uriPLIIS + this.uriAfiliadoIdAfiliado
            + `/?idAfiliado=${idAfiliado}`);
    }

    public getCatalogoRelacionesFamiliares(): Observable<RelacionFamiliarDTO[]> {
        return this.httpClient.get<RelacionFamiliarDTO[]>(this.uriPLIIS + this.uriCatalogoRelacionesFamiliares);
    }

    public recuperaPasswOlvidada(email: string): Observable<boolean> {
        return this.httpClient.get<boolean>(this.uriPLIIS + this.uriRecuperaPasswOlvidada
            + `/?email=${email}`);
    }

    public cambiaPasswOlvidada(olvidePasswDTO: OlvidePasswDTO): Observable<boolean> {
        return this.httpClient.put<boolean>(this.uriPLIIS + this.uriCambiaPasswOlvidada, olvidePasswDTO);
    }

    public getTodosMisFamiliares(): Observable<FamiliarDTO[]> {
        return this.httpClient.get<FamiliarDTO[]>(this.uriPLIIS + this.uriTodosMisFamiliares);
    }

    public cancelarFamiliar(idVinculoFamiliar: number): Observable<boolean> {
        return this.httpClient.post<boolean>(this.uriPLIIS + this.uriCancelaFamiliar +
            `/?idVinculoFamiliar=${idVinculoFamiliar}`, null);
    }

    public getCatalogoPlanes(): Observable<PlanDTO[]> {
        return this.httpClient.get<PlanDTO[]>(this.uriPLIIS + this.uriCatalogoPlanes);
    }

    public realizaPagoMembrecia(pagoDTO: PagoDTO): Observable<boolean> {
        return this.httpClient.post<boolean>(this.uriPLIIS + this.uriRealizarPagoMembrecia, pagoDTO);
    }

    public getHistorialPagos(): Observable<SuscripcionDTO[]> {
        return this.httpClient.get<SuscripcionDTO[]>(this.uriPLIIS + this.uriHistorialPagos);
    }

    public calcularFiniquito(finiquitoCalculoDTO: FiniquitoCalculoDTO): Observable<FiniquitoDetalleDTO> {
        return this.httpClient.post<FiniquitoDetalleDTO>(this.uriPLIIS + this.uriCalcularFiniquito, finiquitoCalculoDTO);
    }

    public validaSuscripcionActiva(): Observable<SuscripcionDTO> {
        return this.httpClient.get<SuscripcionDTO>(this.uriPLIIS + this.uriValidaSuscripcionActiva);
    }

    public solicitarBoleto(tipoUsuario: string): Observable<BoletoCineDTO> {
        return this.httpClient.post<BoletoCineDTO>(this.uriPLIIS + this.uriSolicitaBoletoCine +
            `/?tipoUsuario=${tipoUsuario}`, null);
    }

    public enviaEmailBoletoCine(folio: string, vigencia: string): Observable<boolean> {
        return this.httpClient.post<boolean>(this.uriPLIIS + this.uriEnviaEmailBoletoCine
            + `/?folio=${folio}&vigencia=${vigencia}`, null);
    }

    public getCatalogoMontoCredito(): Observable<MontoCreditoDTO[]> {
        return this.httpClient.get<MontoCreditoDTO[]>(this.uriPLIIS + this.uriCatalogoMontosCredito);
    }

    public getCatalogoTipoArchivo(): Observable<TipoArchivoDTO[]> {
        return this.httpClient.get<TipoArchivoDTO[]>(this.uriPLIIS + this.uriCatalogoTipoArchivo);
    }

    public getCatalogoEstatusCredito(): Observable<EstatusCreditoDTO[]> {
        return this.httpClient.get<EstatusCreditoDTO[]>(this.uriPLIIS + this.uriCatalogoEstatusCredito);
    }

    public consultaSolicitudesCredito(): Observable<NuevaSolicitudCreditoDTO[]> {
        return this.httpClient.get<NuevaSolicitudCreditoDTO[]>(this.uriPLIIS + this.uriConsultaSolicitudesCredito);
    }

    public guardarObservacionCredito(nuevaObservacionCreditoDTO: ObservacionCreditoDTO): Observable<MensajeDTO> {
        return this.httpClient.post<MensajeDTO>(this.uriPLIIS + this.uriNuevaObservacionCredito, nuevaObservacionCreditoDTO);
    }

    public guardarSolicitudCredito(nuevaSolicitudCreditoDTO: NuevaSolicitudCreditoDTO): Observable<MensajeDTO> {
        return this.httpClient.post<MensajeDTO>(this.uriPLIIS + this.uriNuevoSolicitudCredito, nuevaSolicitudCreditoDTO);
    }

    public modificarSolicitudCredito(nuevaSolicitudCreditoDTO: NuevaSolicitudCreditoDTO): Observable<MensajeDTO> {
        return this.httpClient.post<MensajeDTO>(this.uriPLIIS + this.uriModificaSolicitudCredito, nuevaSolicitudCreditoDTO);
    }

    public nuevoFamiliarBeneficiario(familiarBeneficiarioDTO: FamiliarBeneficiarioDto): Observable<Boolean> {
        return this.httpClient.post<Boolean>(this.uriPLIIS + this.uriFamiliarBeneficiarioRegistro, familiarBeneficiarioDTO);
    }

    public consultaFamiliaresPorUsuarioLogueado(): Observable<FamiliarBeneficiarioDto[]> {
        return this.httpClient.get<FamiliarBeneficiarioDto[]>(this.uriPLIIS + this.uriFamiliarBeneficiarioConsulta);
    }

    public modificaFamiliaresPorUsuarioLogueado(familiarBeneficiarioDTO: FamiliarBeneficiarioDto): Observable<Boolean> {
        return this.httpClient.put<Boolean>(this.uriPLIIS + this.uriFamiliarBeneficiarioModificar,familiarBeneficiarioDTO);
    }

    public eliminarFamiliaresPorUsuarioLogueado(idFamiliarBeneficiario: Number): Observable<Boolean> {
        return this.httpClient.delete<Boolean>(this.uriPLIIS + this.uriFamiliarBeneficiarioEliminar+'?idFamiliarBeneficiario='+idFamiliarBeneficiario);
    }

}
