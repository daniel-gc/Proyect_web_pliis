import {Component, Input, OnInit} from '@angular/core';
import {trigger, state, style, transition, animate} from '@angular/animations';
import {MenuItem} from 'primeng/api/menuitem';
import {AppMainComponent} from './app.main.component';
import {TokenService} from './services/security/token.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-menu',
    template: `
        <ul app-submenu [item]="model" root="true" class="ultima-menu ultima-main-menu clearfix"
            [reset]="reset" visible="true" parentActive="true"></ul>
    `
})
export class AppMenuComponent implements OnInit {

    @Input() reset: boolean;

    model: any[];

    constructor(public app: AppMainComponent, private tokenService: TokenService, private router: Router) {}

    mostrarMenu(rolesAutorizados: string[]): boolean {
        // const rolesAutorizados: string[] = rolesAutorizados;
        const rolesUsuarioLogeado: string[] = this.tokenService.getAuthorities();
        let tieneAcceso = false;
        if (rolesAutorizados.indexOf('ANY') !== -1 && rolesUsuarioLogeado.length > 0) {
            return true;
        }

        /*
        Hay que buscar si alguno de los roles del usuario, contenidos en rolesUsuarioLogeado,
        existe en el arreglo de rolesAutorizados
        * */
        rolesUsuarioLogeado.forEach(rolUsuario => {
            rolesAutorizados.forEach(rolAutorizado => {
                if (rolUsuario === rolAutorizado) {
                    tieneAcceso = true;
                }
            });
        });
        if (!this.tokenService.getToken() || !tieneAcceso) {
            // this.router.navigate(['']);
            return false;
        }
        return true;
    }

    ngOnInit() {
        this.model = [
            // {label: 'Planes de membrecía', icon: 'credit_card', routerLink: ['/planesMembrecia'],
            //     visible: this.mostrarMenu(['ROLE_MIEMBRO_REGISTRADO']) },
            {label: 'Importar datos', icon: 'sync', routerLink: ['/importaDatos'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR'])},
            {label: 'Landing Page', icon: 'flight_land', url: 'assets/pages/index.html', target: '_blank'},
            {label: 'Dashboard', icon: 'account_balance', routerLink: ['/'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR'])},
            {label: 'Estadísticas', icon: 'grid_on', routerLink: ['/estadisticas'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR'])},
            {label: 'Pánico', icon: 'contact_phone', routerLink: ['/panico'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_DELEGADO']) },
            {label: 'Login Page', icon: 'verified_user', routerLink: ['/login'], target: '_blank'},
            {label: 'Registro', icon: 'person_add', routerLink: ['/registro']},
            {label: 'Sindicalismo inteligente', icon: 'school', routerLink: ['/sindicalismoInteligente'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_DELEGADO'])},
            {label: 'Carpeta Sindical', icon: 'folder', routerLink: ['/carpetaSindical'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_DELEGADO'])},
            {label: 'Familiares Serv. Funerarios', icon: 'person_add',
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_FAMILIAR', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN']),
                items: [
                    {label: 'Registro', icon: 'add', routerLink: ['/familiarBeneficiario/registro'] },
                    //{label: 'Modificación', icon: 'update', routerLink: ['/familiarBeneficiario/modificar'] },
                    {label: 'Consulta', icon: 'search', routerLink: ['/familiarBeneficiario/todos'] }
                ]},
            {label: 'Solicitudes Crédito', icon: 'attach_money', routerLink: ['/credito'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO', 'ROLE_ANALISTA_CREDITO', 'ROLE_ANALISTA_ADMIN'])},
            {label: 'Servicios funerarios', icon: 'sentiment_very_dissatisfied', routerLink: ['/beneficios/serviciosFunerarios'],
                visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO'])},
            {label: 'Beneficios', icon: 'card_giftcard', visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']),
                items: [
                    // {label: 'Seguros', icon: 'attach_money', routerLink:  ['/beneficios/seguros']},
                    // {label: 'Servicios', icon: 'room_service', routerLink: ['/beneficios/terceros'] },
                    {label: 'Llamadas ilimitadas', icon: 'call', routerLink: ['/beneficios/llamadasIlimitadas'] },
                    {label: 'Asistencia visual', icon: 'remove_red_eye', routerLink: ['/beneficios/asistenciaVisual'] },
                    {label: 'Asistencia enfermería', icon: 'local_hospital', routerLink: ['/beneficios/cuidadosEnfermeria'] },
                    {label: 'Red veterinaria', icon: 'pets', routerLink: ['/beneficios/redVeterinaria'] },
                    {label: 'Viajes', icon: 'local_airport', routerLink: ['/beneficios/mobileViajes'] },
                ]},
            {label: 'Asistencias', icon: 'live_help', visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']),
                items: [
                    {label: 'Médico telefónico', icon: 'phone_forwarded', routerLink: ['/asistencia/medicoTelefonico'] },
                    {label: 'Nutricionista telefónico', icon: 'free_breakfast', routerLink: ['/asistencia/nutricionistaTelefonico'] },
                    {label: 'Psicólogo telefónico', icon: 'event_seat', routerLink: ['/asistencia/psicologoTelefonico'] },
                    {label: 'Pediatra telefónico', icon: 'child_care', routerLink: ['/asistencia/pediatraTelefonico'] },
                    {label: 'Asistencia a la mujer', icon: 'pregnant_woman', routerLink: ['/asistencia/asistenciaMujer'] },
                    {label: 'Ambulancia', icon: 'airport_shuttle', routerLink: ['/asistencia/asistenciaAmbulancia'] },
                    {label: 'Check up médico', icon: 'search', routerLink: ['/asistencia/checkup'] },
                    {label: 'Descuentos médicos', icon: 'attach_money', routerLink: ['/asistencia/descuentosMedicos'] },
                    {label: 'Servicio de vidriería', icon: 'aspect_ratio', routerLink: ['/asistencia/vidrieria'] },
                    {label: 'Cerrajería', icon: 'lock', routerLink: ['/asistencia/cerrajeria'] },
                    {label: 'Electricidad', icon: 'flash_off', routerLink: ['/asistencia/electricidad'] },
                    {label: 'Plomería', icon: 'format_color_reset', routerLink: ['/asistencia/plomeria'] },
                ]},
            {label: 'Academia', icon: 'card_membership', routerLink: ['/academia'], visible: this.mostrarMenu(['ROLE_ADMINISTRADOR', 'ROLE_AFILIADO', 'ROLE_FAMILIAR', 'ROLE_DELEGADO', 'ROLE_MIEMBRO_ACTIVO']) },
            // {
            //     label: 'Temas', icon: 'palette',
            //     items: [
            //         {label: 'Indigo - Pink', icon: 'brush', command: (event) => {this.changeTheme('indigo'); }},
            //         {label: 'Brown - Green', icon: 'brush', command: (event) => {this.changeTheme('brown'); }},
            //         {label: 'Blue - Amber', icon: 'brush', command: (event) => {this.changeTheme('blue'); }},
            //         {label: 'Blue Grey - Green', icon: 'brush', command: (event) => {this.changeTheme('blue-grey'); }},
            //         {label: 'Dark - Blue', icon: 'brush', command: (event) => {this.changeTheme('dark-blue'); }},
            //         {label: 'Dark - Green', icon: 'brush', command: (event) => {this.changeTheme('dark-green'); }},
            //         {label: 'Green - Yellow', icon: 'brush', command: (event) => {this.changeTheme('green'); }},
            //         {label: 'Purple - Cyan', icon: 'brush', command: (event) => {this.changeTheme('purple-cyan'); }},
            //         {label: 'Purple - Amber', icon: 'brush', command: (event) => {this.changeTheme('purple-amber'); }},
            //         {label: 'Teal - Lime', icon: 'brush', command: (event) => {this.changeTheme('teal'); }},
            //         {label: 'Cyan - Amber', icon: 'brush', command: (event) => {this.changeTheme('cyan'); }},
            //         {label: 'Grey - Deep Orange', icon: 'brush', command: (event) => {this.changeTheme('grey'); }}
            //     ]
            // },
            // {
            //     label: 'Customization', icon: 'settings_application',
            //     items: [
            //         {label: 'Compact Size', icon: 'fiber_manual_record', command: () => this.app.layoutCompact = true},
            //         {label: 'Material Size', icon: 'fiber_smart_record',  command: () => this.app.layoutCompact = false},
            //         {label: 'Static Menu', icon: 'menu',  command: () => this.app.changeToStaticMenu()},
            //         {label: 'Overlay Menu', icon: 'exit_to_app',  command: () => this.app.changeToOverlayMenu()},
            //         {label: 'Slim Menu', icon: 'more_vert',  command: () => this.app.changeToSlimMenu()},
            //         {label: 'Horizontal Menu', icon: 'border_horizontal',  command: () => this.app.changeToHorizontalMenu()},
            //         {label: 'Light Menu', icon: 'label_outline',  command: () => this.app.darkMenu = false},
            //         {label: 'Dark Menu', icon: 'label',  command: () => this.app.darkMenu = true},
            //         {label: 'Inline Profile', icon: 'contacts',  command: () => this.app.profileMode = 'inline'},
            //         {label: 'Top Profile', icon: 'person_pin',  command: () => this.app.profileMode = 'top'},
            //     ]
            // },
            // {
            //     label: 'Components', icon: 'list', badge: '2', badgeStyleClass: 'teal-badge',
            //     items: [
            //         {label: 'Sample Page', icon: 'desktop_mac', routerLink: ['/sample']},
            //         {label: 'Forms', icon: 'input', routerLink: ['/forms']},
            //         {label: 'Data', icon: 'grid_on', routerLink: ['/data']},
            //         {label: 'Panels', icon: 'content_paste', routerLink: ['/panels']},
            //         {label: 'Overlays', icon: 'content_copy', routerLink: ['/overlays']},
            //         {label: 'Menus', icon: 'menu', routerLink: ['/menus']},
            //         {label: 'Messages', icon: 'message', routerLink: ['/messages']},
            //         {label: 'Charts', icon: 'insert_chart', routerLink: ['/charts']},
            //         {label: 'File', icon: 'attach_file', routerLink: ['/file']},
            //         {label: 'Misc', icon: 'toys', routerLink: ['/misc']}
            //     ]
            // },
            // {
            //     label: 'Template Pages', icon: 'get_app',
            //     items: [
            //         {label: 'Empty Page', icon: 'hourglass_empty', routerLink: ['/empty']},
            //         {label: 'Landing Page', icon: 'flight_land', url: 'assets/pages/landing.html', target: '_blank'},
            //         {label: 'Login Page', icon: 'verified_user', routerLink: ['/login'], target: '_blank'},
            //         {label: 'Error Page', icon: 'error', routerLink: ['/error'], target: '_blank'},
            //         {label: '404 Page', icon: 'error_outline', routerLink: ['/404'], target: '_blank'},
            //         {label: 'Access Denied Page', icon: 'security', routerLink: ['/accessdenied'], target: '_blank'}
            //     ]
            // },
            // {
            //     label: 'Menu Hierarchy', icon: 'menu',
            //     items: [
            //         {
            //             label: 'Submenu 1', icon: 'subject',
            //             items: [
            //                 {
            //                     label: 'Submenu 1.1', icon: 'subject',
            //                     items: [
            //                         {label: 'Submenu 1.1.1', icon: 'subject'},
            //                         {label: 'Submenu 1.1.2', icon: 'subject'},
            //                         {label: 'Submenu 1.1.3', icon: 'subject'},
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 1.2', icon: 'subject',
            //                     items: [
            //                         {label: 'Submenu 1.2.1', icon: 'subject'},
            //                         {label: 'Submenu 1.2.2', icon: 'subject'}
            //                     ]
            //                 },
            //             ]
            //         },
            //         {
            //             label: 'Submenu 2', icon: 'subject',
            //             items: [
            //                 {
            //                     label: 'Submenu 2.1', icon: 'subject',
            //                     items: [
            //                         {label: 'Submenu 2.1.1', icon: 'subject'},
            //                         {label: 'Submenu 2.1.2', icon: 'subject'},
            //                         {label: 'Submenu 2.1.3', icon: 'subject'},
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 2.2', icon: 'subject',
            //                     items: [
            //                         {label: 'Submenu 2.2.1', icon: 'subject'},
            //                         {label: 'Submenu 2.2.2', icon: 'subject'}
            //                     ]
            //                 },
            //             ]
            //         }
            //     ]
            // },
            // {label: 'Utils', icon: 'build', routerLink: ['/utils']},
            // {label: 'Documentation', icon: 'find_in_page', routerLink: ['/documentation']}
        ];
    }

    changeTheme(theme) {
        const themeLink: HTMLLinkElement = document.getElementById('theme-css') as HTMLLinkElement ;
        const layoutLink: HTMLLinkElement = document.getElementById('layout-css') as HTMLLinkElement ;

        themeLink.href = 'assets/theme/theme-' + theme + '.css';
        layoutLink.href = 'assets/layout/css/layout-' + theme + '.css';
    }
}

@Component({
  /* tslint:disable:component-selector */
    selector: '[app-submenu]',
  /* tslint:enable:component-selector */
    template: `
        <ng-template ngFor let-child let-i="index" [ngForOf]="(root ? item : item.items)">
            <li [ngClass]="{'active-menuitem': isActive(i)}" [class]="child.badgeStyleClass" *ngIf="child.visible === false ? false : true">
                <a [href]="child.url||'#'" (click)="itemClick($event,child,i)" (mouseenter)="onMouseEnter(i)"
                   class="ripplelink" *ngIf="!child.routerLink"
                    [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target">
                    <i *ngIf="child.icon" class="material-icons">{{child.icon}}</i>
                    <span>{{child.label}}</span>
                    <span class="menuitem-badge" *ngIf="child.badge">{{child.badge}}</span>
                    <i class="material-icons submenu-icon" *ngIf="child.items">keyboard_arrow_down</i>
                </a>

                <a (click)="itemClick($event,child,i)" (mouseenter)="onMouseEnter(i)" class="ripplelink" *ngIf="child.routerLink"
                    [routerLink]="child.routerLink" routerLinkActive="active-menuitem-routerlink"
                   [routerLinkActiveOptions]="{exact: true}" [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target">
                    <i *ngIf="child.icon" class="material-icons">{{child.icon}}</i>
                    <span>{{child.label}}</span>
                    <span class="menuitem-badge" *ngIf="child.badge">{{child.badge}}</span>
                    <i class="material-icons submenu-icon" *ngIf="child.items">keyboard_arrow_down</i>
                </a>
                <div class="layout-menu-tooltip">
                    <div class="layout-menu-tooltip-arrow"></div>
                    <div class="layout-menu-tooltip-text">{{child.label}}</div>
                </div>
                <ul app-submenu [item]="child" *ngIf="child.items" [visible]="isActive(i)" [reset]="reset" [parentActive]="isActive(i)"
                    [@children]="(app.isSlim()||app.isHorizontal())&&root ? isActive(i) ?
                    'visible' : 'hidden' : isActive(i) ? 'visibleAnimated' : 'hiddenAnimated'"></ul>
            </li>
        </ng-template>
    `,
    animations: [
        trigger('children', [
            state('hiddenAnimated', style({
                height: '0px'
            })),
            state('visibleAnimated', style({
                height: '*'
            })),
            state('visible', style({
                height: '*',
              'z-index': 100
            })),
            state('hidden', style({
                height: '0px',
              'z-index': '*'
            })),
            transition('visibleAnimated => hiddenAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
            transition('hiddenAnimated => visibleAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class AppSubMenuComponent {

    @Input() item: MenuItem;

    @Input() root: boolean;

    @Input() visible: boolean;

    _reset: boolean;

    _parentActive: boolean;

    activeIndex: number;

    constructor(public app: AppMainComponent) {}

    itemClick(event: Event, item: MenuItem, index: number) {
        if (this.root) {
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }

        // avoid processing disabled items
        if (item.disabled) {
            event.preventDefault();
            return true;
        }

        // activate current item and deactivate active sibling if any
        this.activeIndex = (this.activeIndex === index && this.root) ? null : index;

        // execute command
        if (item.command) {
            item.command({originalEvent: event, item});
        }

        // prevent hash change
        if (item.items || (!item.url && !item.routerLink)) {
            setTimeout(() => {
              this.app.layoutMenuScrollerViewChild.moveBar();
            }, 450);
            event.preventDefault();
        }

        // hide menu
        if (!item.items) {
            if (this.app.isHorizontal() || this.app.isSlim()) {
                this.app.resetMenu = true; } else {
                this.app.resetMenu = false; }

            this.app.overlayMenuActive = false;
            this.app.staticMenuMobileActive = false;
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }
    }

    onMouseEnter(index: number) {
        if (this.root && this.app.menuHoverActive && (this.app.isHorizontal() || this.app.isSlim())
          && !this.app.isMobile() && !this.app.isTablet()) {
            this.activeIndex = index;
        }
    }

    isActive(index: number): boolean {
        return this.activeIndex === index;
    }

    @Input() get reset(): boolean {
        return this._reset;
    }

    set reset(val: boolean) {
        this._reset = val;

        if (this._reset && (this.app.isHorizontal() || this.app.isSlim())) {
            this.activeIndex = null;
        }
    }

    @Input() get parentActive(): boolean {
        return this._parentActive;
    }

    set parentActive(val: boolean) {
        this._parentActive = val;

        if (!this._parentActive) {
            this.activeIndex = null;
        }
    }
}
