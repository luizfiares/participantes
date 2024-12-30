import { AbstractControl, ValidationErrors } from '@angular/forms';

export function cpfValidator(control: AbstractControl): ValidationErrors | null {
  const cpf = control.value?.replace(/\D/g, ''); 

  if (!cpf || cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) {
    console.log("cpfInvalido");
    return { cpfInvalido: true };
  }

  let soma = 0;
  let resto;

  for (let i = 0; i < 9; i++) {
    soma += parseInt(cpf.charAt(i), 10) * (10 - i);
  }

  resto = soma % 11;
  if (resto < 2) {
    resto = 0;
  } else {
    resto = 11 - resto;
  }

  if (parseInt(cpf.charAt(9), 10) !== resto) {
    return { cpfInvalido: true };   
  }

  soma = 0;
  for (let i = 0; i < 10; i++) {
    soma += parseInt(cpf.charAt(i), 10) * (11 - i);
  }

  resto = soma % 11;
  if (resto < 2) {
    resto = 0;
  } else {
    resto = 11 - resto;
  }

  if (parseInt(cpf.charAt(10), 10) !== resto) {
    return { cpfInvalido: true };
  }

  return null; 
}
