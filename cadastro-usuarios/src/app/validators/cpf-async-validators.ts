import { AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { debounceTime, map, switchMap } from 'rxjs/operators';

export function cpfAsyncValidator(control: AbstractControl): Observable<ValidationErrors | null> {
  const cpf = control.value?.replace(/\D/g, ''); 
  if (!cpf || cpf.length !== 11) {
    return of({ cpfInvalido: true }); 
  }
  return of(null); 
}
