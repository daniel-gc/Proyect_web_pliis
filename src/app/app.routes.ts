import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { AppNotfoundComponent } from './pages/app.notfound.component';
import { AppErrorComponent } from './pages/app.error.component';
import { AppAccessdeniedComponent } from './pages/app.accessdenied.component';
import { AppLoginComponent } from './pages/app.login.component';
import {RegistroUsuarioComponent} from './demo/view/registro-usuario/registro-usuario.component';
import {BotonPanicoComponent} from './demo/view/boton-panico/boton-panico.component';
import {TercerosComponent} from './demo/view/terceros/terceros.component';
import {EstadisticasComponent} from './demo/view/estadisticas/estadisticas.component';
import {AcademiaComponent} from './demo/view/academia/academia.component';
import {SegurosComponent} from './demo/view/seguros/seguros.component';
import {ServFunerariosComponent} from './demo/view/serv-funerarios/serv-funerarios.component';
import {BeneficiosComponent} from './demo/view/beneficios/beneficios.component';
import {MedicoTelefonicoComponent} from './demo/view/asistencias/medico-telefonico/medico-telefonico.component';
import {NutricionistaComponent} from './demo/view/asistencias/nutricionista/nutricionista.component';
import {PsicologoComponent} from './demo/view/asistencias/psicologo/psicologo.component';
import {PediatraComponent} from './demo/view/asistencias/pediatra/pediatra.component';
import {AsistenciaMujerComponent} from './demo/view/asistencias/asistencia-mujer/asistencia-mujer.component';
import {AmbulanciaComponent} from './demo/view/asistencias/ambulancia/ambulancia.component';
import {CheckupMedicoComponent} from './demo/view/asistencias/checkup-medico/checkup-medico.component';
import {SindicalismoComponent} from './demo/view/sindicalismo/sindicalismo.component';
import {DescuentoMedicoComponent} from './demo/view/asistencias/descuento-medico/descuento-medico.component';
import {VidrieriaComponent} from './demo/view/asistencias/vidrieria/vidrieria.component';
import {CerrajeriaComponent} from './demo/view/asistencias/cerrajeria/cerrajeria.component';
import {ElectricidadComponent} from './demo/view/asistencias/electricidad/electricidad.component';
import {PlomeriaComponent} from './demo/view/asistencias/plomeria/plomeria.component';
import {LogoutComponent} from './pages/logout/logout.component';
import {GuardService as guard} from './guards/guard.service';
import {CarpetaSindicalComponent} from './demo/view/carpeta-sindical/carpeta-sindical.component';
import {CambioPasswComponent} from './demo/view/passwchg/cambio-passw/cambio-passw.component';
import {BeneficiariosComponent} from './demo/view/beneficiarios/beneficiarios/beneficiarios.component';
import {RegistroBeneficiarioComponent} from './demo/view/registro-beneficiario/registro-beneficiario.component';
import {OlvidePasswComponent} from './demo/view/olvide-passw/olvide-passw.component';
import {DashboardComponent} from './demo/view/dashboard/dashboard.component';
import {ImportaDatosComponent} from './demo/view/empresas_sindicatos/importa-datos/importa-datos.component';
import {RegistroMiembroComponent} from './demo/view/registro-miembro/registro-miembro.component';
import {PlanesMembreciaComponent} from './demo/view/planes-membrecia/planes-membrecia.component';
import {HistorialPagoComponent} from './demo/view/historial-pago/historial-pago.component';
import {CalculoFiniquitoComponent} from './demo/view/calculo-finiquito/calculo-finiquito.component';
import {BoletosCineComponent} from './demo/view/boletos-cine/boletos-cine.component';
import {CreditoComponent} from './demo/view/creditos/credito.component';
import {FamiliarAltaComponent} from './demo/view/familiares-beneficiario/familiar-alta/familiar-alta.component';
import {FamiliarModificacionComponent} from './demo/view/familiares-beneficiario/familiar-modificacion/familiar-modificacion.component';
import {FamiliarConsultaComponent} from './demo/view/familiares-beneficiario/familiar-consulta/familiar-consulta.component';

export const routes: Routes = [
    { path: '', component: AppMainComponent,
        children: [
            { path: '', component: DashboardComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ANY']}},
            { path: 'importaDatos', component: ImportaDatosComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR']}},
            {path: 'cambioContrasena', component: CambioPasswComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ANY']}},
            {path: 'beneficiarios', component: BeneficiariosComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO']}},
            {path: 'panico', component: BotonPanicoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO']}},
            {path: 'estadisticas', component: EstadisticasComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR']}},
            {path: 'academia', component: AcademiaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']}, },
            {path: 'beneficios/serviciosFunerarios', component: ServFunerariosComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']}, },
            {path: 'beneficios', component: BeneficiosComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']}},
            {path: 'asistencia/medicoTelefonico', component: MedicoTelefonicoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/nutricionistaTelefonico', component: NutricionistaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/psicologoTelefonico', component: PsicologoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/pediatraTelefonico', component: PediatraComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/asistenciaMujer', component: AsistenciaMujerComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/asistenciaAmbulancia', component: AmbulanciaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/checkup', component: CheckupMedicoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/descuentosMedicos', component: DescuentoMedicoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/vidrieria', component: VidrieriaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/cerrajeria', component: CerrajeriaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/electricidad', component: ElectricidadComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'asistencia/plomeria', component: PlomeriaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'sindicalismoInteligente', component: SindicalismoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ANY']}},
            {path: 'carpetaSindical', component: CarpetaSindicalComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_DELEGADO', 'ANY']}},
            {path: 'planesMembrecia', component: PlanesMembreciaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_MIEMBRO_REGISTRADO', 'ROLE_MIEMBRO_ACTIVO']}},
            {path: 'historialPago', component: HistorialPagoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_MIEMBRO_REGISTRADO', 'ROLE_MIEMBRO_ACTIVO']}},
            {path: 'calculoFiniquito', component: CalculoFiniquitoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']}},
            {path: 'boletosCine', component: BoletosCineComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']}},
            {path: 'credito', component: CreditoComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN']}},
            {path: 'familiarBeneficiario/registro', component: FamiliarAltaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_FAMILIAR','ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN']}},
            {path: 'familiarBeneficiario/modificar', component: FamiliarModificacionComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_FAMILIAR','ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN']}},
            {path: 'familiarBeneficiario/todos', component: FamiliarConsultaComponent,
                canActivate: [guard], data: { rolesAutorizados: ['ROLE_FAMILIAR','ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN']}}

        ]
    },
    {path: 'error', component: AppErrorComponent},
    {path: 'accessdenied', component: AppAccessdeniedComponent},
    {path: '404', component: AppNotfoundComponent},
    {path: 'login', component: AppLoginComponent},
    {path: 'registro', component: RegistroUsuarioComponent},
    {path: 'olvidePassw', component: OlvidePasswComponent},
    {path: 'registroBeneficiarios', component: RegistroBeneficiarioComponent},
    {path: 'beneficiarios', component: BeneficiariosComponent},
    {path: 'registroMiembros', component: RegistroMiembroComponent},
    {path: 'logout/:href', component: LogoutComponent },
    {path: '**', redirectTo: '/404'},
    // {path: '',  'assets/pages/landing.html'},

    // {path: '',  redirectTo: 'assets/pages/landing.html'},
];

export const AppRoutes: ModuleWithProviders<any> = RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'});
