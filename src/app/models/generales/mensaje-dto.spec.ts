import { MensajeDTO } from './mensaje-dto';

describe('MensajeDTO', () => {
  it('should create an instance', () => {
    expect(new MensajeDTO('Hola')).toBeTruthy();
  });
});
