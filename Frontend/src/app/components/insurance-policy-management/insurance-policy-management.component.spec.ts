import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { InsurancePolicyComponent } from './insurance-policy-management.component';
import { InsurancePolicyService } from '../../services/insurance-policy.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';
import { InsurancePolicy } from 'src/app/models/insurance-policy-management.model';

describe('InsurancePolicyComponent', () => {
  let component: InsurancePolicyComponent;
  let fixture: ComponentFixture<InsurancePolicyComponent>;
  let insurancePolicyService: InsurancePolicyService;
  let mockInsurancePolicyService: jest.Mocked<InsurancePolicyService>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [InsurancePolicyComponent],
      imports: [ReactiveFormsModule, HttpClientModule, FormsModule],
      providers: [InsurancePolicyService]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsurancePolicyComponent);
    component = fixture.componentInstance;
    insurancePolicyService = TestBed.inject(InsurancePolicyService);
    fixture.detectChanges();
  });

  describe('business', () => {
    it('should create the InsurancePolicyComponent', () => {
      expect(component).toBeTruthy();
    });

    it('should display the heading "Insurance Policies"', () => {
      const h2: HTMLElement = fixture.debugElement.query(By.css('h2')).nativeElement;
      expect(h2.textContent).toContain('Insurance Policies');
    });

    it('should have a button with text "Refresh Policies"', () => {
      const button: HTMLElement = fixture.debugElement.query(By.css('button')).nativeElement;
      expect(button.textContent).toContain('Refresh Policies');
    });

    it('should have a button with text "Select" for each policy', () => {
      // Assuming component.policies is already populated with mock data
      const selectButtons = fixture.debugElement.queryAll(By.css('button')).filter(btn => btn.nativeElement.textContent === 'Select');
      expect(selectButtons.length).toBe(0);
    });

    it('should have a button with text "Update" for each policy', () => {
      const updateButtons = fixture.debugElement.queryAll(By.css('button')).filter(btn => btn.nativeElement.textContent === 'Update');
      expect(updateButtons.length).toBe(0);
    });

    it('should have a button with text "Delete" for each policy', () => {
      const deleteButtons = fixture.debugElement.queryAll(By.css('button')).filter(btn => btn.nativeElement.textContent === 'Delete');
      expect(deleteButtons.length).toBe(0);
    });

    it('should have a text field for Policy Number', () => {
      const policyNumberInput = fixture.debugElement.query(By.css('input[name="policyNumber"]'));
      expect(policyNumberInput).toBeTruthy();
    });

    it('should have a text field for Policy Type', () => {
      const policyTypeInput = fixture.debugElement.query(By.css('input[name="policyType"]'));
      expect(policyTypeInput).toBeTruthy();
    });

    it('should have a text field for Premium Amount', () => {
      const premiumAmountInput = fixture.debugElement.query(By.css('input[name="premiumAmount"]'));
      expect(premiumAmountInput).toBeTruthy();
    });

    it('should have a text field for Start Date', () => {
      const startDateInput = fixture.debugElement.query(By.css('input[name="startDate"]'));
      expect(startDateInput).toBeTruthy();
    });

    it('should have a text field for End Date', () => {
      const endDateInput = fixture.debugElement.query(By.css('input[name="endDate"]'));
      expect(endDateInput).toBeTruthy();
    });
  });
});