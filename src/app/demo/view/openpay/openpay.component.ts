declare const OpenPay: any

export class OpenpayComponent {
    
    constructor(){
        OpenPay.setId('mamqzk5dmtvlaxei1b1o');
        OpenPay.setApiKey('pk_51a20b2f94b54261842e12f26018f74e');
        OpenPay.setSandboxMode(true);
        console.log('key set');
    }

    public getOpenPay() : any {
        return OpenPay;
    }
}