import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { AppRoutes } from './app.routes';

import { AccordionModule } from 'primeng/accordion';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { CarouselModule } from 'primeng/carousel';
import { ChartModule } from 'primeng/chart';
import { CheckboxModule } from 'primeng/checkbox';
import { ChipsModule } from 'primeng/chips';
import { CodeHighlighterModule } from 'primeng/codehighlighter';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ColorPickerModule } from 'primeng/colorpicker';
import { ContextMenuModule } from 'primeng/contextmenu';
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { EditorModule } from 'primeng/editor';
import { FieldsetModule } from 'primeng/fieldset';
import { FileUploadModule } from 'primeng/fileupload';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { GalleriaModule } from 'primeng/galleria';
import { InplaceModule } from 'primeng/inplace';
import { InputMaskModule } from 'primeng/inputmask';
import { InputSwitchModule } from 'primeng/inputswitch';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { LightboxModule } from 'primeng/lightbox';
import { ListboxModule } from 'primeng/listbox';
import { MegaMenuModule } from 'primeng/megamenu';
import { MenuModule } from 'primeng/menu';
import { MenubarModule } from 'primeng/menubar';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { MultiSelectModule } from 'primeng/multiselect';
import { OrderListModule } from 'primeng/orderlist';
import { OrganizationChartModule } from 'primeng/organizationchart';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { PaginatorModule } from 'primeng/paginator';
import { PanelModule } from 'primeng/panel';
import { PanelMenuModule } from 'primeng/panelmenu';
import { PasswordModule } from 'primeng/password';
import { PickListModule } from 'primeng/picklist';
import { ProgressBarModule } from 'primeng/progressbar';
import { RadioButtonModule } from 'primeng/radiobutton';
import { RatingModule } from 'primeng/rating';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { SelectButtonModule } from 'primeng/selectbutton';
import { SlideMenuModule } from 'primeng/slidemenu';
import { SliderModule } from 'primeng/slider';
import { SpinnerModule } from 'primeng/spinner';
import { SplitButtonModule } from 'primeng/splitbutton';
import { StepsModule } from 'primeng/steps';
import { TabMenuModule } from 'primeng/tabmenu';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { TerminalModule } from 'primeng/terminal';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { ToastModule } from 'primeng/toast';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';
import { TreeModule } from 'primeng/tree';
import { TreeTableModule } from 'primeng/treetable';
import { VirtualScrollerModule } from 'primeng/virtualscroller';
import {MenuItem} from 'primeng/api';
import {MessageService} from 'primeng/api/messageservice';

import { AppComponent} from './app.component';
import { AppMainComponent } from './app.main.component';
import { AppNotfoundComponent } from './pages/app.notfound.component';
import { AppErrorComponent } from './pages/app.error.component';
import { AppAccessdeniedComponent } from './pages/app.accessdenied.component';
import { AppLoginComponent } from './pages/app.login.component';
import { AppMenuComponent, AppSubMenuComponent} from './app.menu.component';
import { AppTopbarComponent } from './app.topbar.component';
import { AppFooterComponent } from './app.footer.component';
import { AppBreadcrumbComponent } from './app.breadcrumb.component';
import { AppRightpanelComponent } from './app.rightpanel.component';
import { AppInlineProfileComponent } from './app.profile.component';
// import { NgImageFullscreenViewModule } from 'ng-image-fullscreen-view';

import { SignaturePadModule } from 'angular2-signaturepad';

import { CarService } from './demo/service/carservice';
import { CountryService } from './demo/service/countryservice';
import { EventService } from './demo/service/eventservice';
import { NodeService } from './demo/service/nodeservice';
import { BreadcrumbService } from './breadcrumb.service';
import { RegistroUsuarioComponent } from './demo/view/registro-usuario/registro-usuario.component';
import { BotonPanicoComponent } from './demo/view/boton-panico/boton-panico.component';
import { TercerosComponent } from './demo/view/terceros/terceros.component';
import { EstadisticasComponent } from './demo/view/estadisticas/estadisticas.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SegurosComponent } from './demo/view/seguros/seguros.component';
import { ServFunerariosComponent } from './demo/view/serv-funerarios/serv-funerarios.component';
import { AcademiaComponent } from './demo/view/academia/academia.component';
import { BeneficiosComponent } from './demo/view/beneficios/beneficios.component';
import { MedicoTelefonicoComponent } from './demo/view/asistencias/medico-telefonico/medico-telefonico.component';
import { NutricionistaComponent } from './demo/view/asistencias/nutricionista/nutricionista.component';
import { PsicologoComponent } from './demo/view/asistencias/psicologo/psicologo.component';
import { PediatraComponent } from './demo/view/asistencias/pediatra/pediatra.component';
import { AsistenciaMujerComponent } from './demo/view/asistencias/asistencia-mujer/asistencia-mujer.component';
import { AmbulanciaComponent } from './demo/view/asistencias/ambulancia/ambulancia.component';
import { CheckupMedicoComponent } from './demo/view/asistencias/checkup-medico/checkup-medico.component';
import { SindicalismoComponent } from './demo/view/sindicalismo/sindicalismo.component';
import { DescuentoMedicoComponent } from './demo/view/asistencias/descuento-medico/descuento-medico.component';
import { VidrieriaComponent } from './demo/view/asistencias/vidrieria/vidrieria.component';
import { CerrajeriaComponent } from './demo/view/asistencias/cerrajeria/cerrajeria.component';
import { ElectricidadComponent } from './demo/view/asistencias/electricidad/electricidad.component';
import { PlomeriaComponent } from './demo/view/asistencias/plomeria/plomeria.component';
import { LogoutComponent } from './pages/logout/logout.component';
import {interceptorProvider} from './interceptors/token-interceptor.service';
import {ApplicationInitService} from './services/application-init.service';
import {BlockUIModule} from 'ng-block-ui';
import { CarpetaSindicalComponent } from './demo/view/carpeta-sindical/carpeta-sindical.component';
import { CambioPasswComponent } from './demo/view/passwchg/cambio-passw/cambio-passw.component';
import { BeneficiariosComponent } from './demo/view/beneficiarios/beneficiarios/beneficiarios.component';
import { RegistroBeneficiarioComponent } from './demo/view/registro-beneficiario/registro-beneficiario.component';
import { OlvidePasswComponent } from './demo/view/olvide-passw/olvide-passw.component';
import { DashboardComponent } from './demo/view/dashboard/dashboard.component';
import { ImportaDatosComponent } from './demo/view/empresas_sindicatos/importa-datos/importa-datos.component';
import { RegistroMiembroComponent } from './demo/view/registro-miembro/registro-miembro.component';
import { PlanesMembreciaComponent } from './demo/view/planes-membrecia/planes-membrecia.component';
import { HistorialPagoComponent } from './demo/view/historial-pago/historial-pago.component';
import { CalculoFiniquitoComponent } from './demo/view/calculo-finiquito/calculo-finiquito.component';
import { BoletosCineComponent } from './demo/view/boletos-cine/boletos-cine.component';
import { CreditoComponent } from './demo/view/creditos/credito.component';
import { FamiliarAltaComponent } from './demo/view/familiares-beneficiario/familiar-alta/familiar-alta.component';
import { FamiliarModificacionComponent } from './demo/view/familiares-beneficiario/familiar-modificacion/familiar-modificacion.component';
import {FamiliarConsultaComponent} from './demo/view/familiares-beneficiario/familiar-consulta/familiar-consulta.component';
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutes,
        HttpClientModule,
        BrowserAnimationsModule,
        AccordionModule,
        AutoCompleteModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        CarouselModule,
        ChartModule,
        CheckboxModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        ColorPickerModule,
        ContextMenuModule,
        DataViewModule,
        DialogModule,
        DropdownModule,
        EditorModule,
        FieldsetModule,
        FileUploadModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessageModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OrganizationChartModule,
        OverlayPanelModule,
        PaginatorModule,
        PanelModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        RatingModule,
        ScrollPanelModule,
        SelectButtonModule,
        SlideMenuModule,
        SliderModule,
        SpinnerModule,
        SplitButtonModule,
        StepsModule,
        TableModule,
        TabMenuModule,
        TabViewModule,
        TerminalModule,
        TieredMenuModule,
        ToastModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        VirtualScrollerModule,
        SignaturePadModule,
        FontAwesomeModule,
        BlockUIModule.forRoot(),
        // NgImageFullscreenViewModule,
    ],
    declarations: [
        AppComponent,
        AppMainComponent,
        AppMenuComponent,
        AppSubMenuComponent,
        AppTopbarComponent,
        AppFooterComponent,
        AppBreadcrumbComponent,
        AppRightpanelComponent,
        AppInlineProfileComponent,
        AppNotfoundComponent,
        AppErrorComponent,
        AppAccessdeniedComponent,
        AppLoginComponent,
        RegistroUsuarioComponent,
        BotonPanicoComponent,
        TercerosComponent,
        EstadisticasComponent,
        SegurosComponent,
        ServFunerariosComponent,
        AcademiaComponent,
        BeneficiosComponent,
        MedicoTelefonicoComponent,
        NutricionistaComponent,
        PsicologoComponent,
        PediatraComponent,
        AsistenciaMujerComponent,
        AmbulanciaComponent,
        CheckupMedicoComponent,
        SindicalismoComponent,
        DescuentoMedicoComponent,
        VidrieriaComponent,
        CerrajeriaComponent,
        ElectricidadComponent,
        PlomeriaComponent,
        LogoutComponent,
        CarpetaSindicalComponent,
        CambioPasswComponent,
        BeneficiariosComponent,
        RegistroBeneficiarioComponent,
        OlvidePasswComponent,
        DashboardComponent,
        ImportaDatosComponent,
        RegistroMiembroComponent,
        PlanesMembreciaComponent,
        HistorialPagoComponent,
        CalculoFiniquitoComponent,
        BoletosCineComponent,
        CreditoComponent,
        FamiliarAltaComponent,
        FamiliarModificacionComponent,
        FamiliarConsultaComponent,
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        CarService, CountryService, EventService, NodeService, BreadcrumbService,
        interceptorProvider,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
