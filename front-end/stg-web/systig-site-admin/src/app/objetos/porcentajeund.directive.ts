import { Directive, HostListener, AfterViewInit, ElementRef, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { PercentMaskService } from './percent-mask.service';

const noop = () => {};

export const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR_PERCENT: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => PorcentajeundDirective),
  multi: true
};

@Directive({
  selector: '[stgPorcentajeund]',
  providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR_PERCENT]
})
export class PorcentajeundDirective implements AfterViewInit, ControlValueAccessor{

  private el: HTMLInputElement;
  private innerValue: any = 0;
  constructor(private elementRef: ElementRef, private percentMaskService: PercentMaskService) {
    this.el = elementRef.nativeElement;
  }

  // Placeholders for the callbacks which are later providesd
  // by the Control Value Accessor
  private onTouchedCallback: () => void = noop;
  private onChangeCallback: (a: any) => void = noop;

  // set getter
  get value(): any {
    return this.innerValue;
  }

  // set accessor including call the onchange callback
  set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCallback(v);
    }
  }

  // Set touched on blur
  // onBlur() {
  //   this.onTouchedCallback();
  // }

  // From ControlValueAccessor interface
  writeValue(value: any) {
    if (value !== this.innerValue) {
      this.el.value = this.percentMaskService.transform(value);
      this.innerValue = value;
    }
  }

  // From ControlValueAccessor interface
  registerOnChange(fn: any) {
    this.onChangeCallback = fn;
  }

  // From ControlValueAccessor interface
  registerOnTouched(fn: any) {
    this.onTouchedCallback = fn;
  }

  ngAfterViewInit() {
    this.el.style.textAlign = 'right';
  }

  // On Focus remove all non-digit or decimal separator values
  @HostListener('focus', ['$event.target.value'])
  onfocus(value) {
    this.el.value = this.percentMaskService.parse(value);
  }

  // On Blue remove all symbols except last . and set to currency format
  @HostListener('blur', ['$event.target.value'])
  onBlur(value) {
    this.onTouchedCallback();
    this.el.value = this.percentMaskService.transform(value);
    this.onChangeCallback(this.percentMaskService.parse(this.el.value));
  }

  // On Change remove all symbols except last . and set to currency format
  @HostListener('change', ['$event.target.value'])
  onChange(value) {
    this.el.value = this.percentMaskService.transform(value);
    this.onChangeCallback(this.percentMaskService.parse(this.el.value));
  }

  // Prevent user to enter anything but digits and decimal separator
  @HostListener('keypress', ['$event'])
  onKeyPress(event) {
    const key = event.which || event.keyCode || 0;
    if (key !== 46 && key > 31 && (key < 48 || key > 57)) {
      event.preventDefault();
    }
  }

}
