import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InsurancePolicyService } from './insurance-policy.service';
import { InsurancePolicy } from '../models/insurance-policy-management.model';

describe('InsurancePolicyService', () => {
  let service: InsurancePolicyService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [InsurancePolicyService]
    });
    service = TestBed.inject(InsurancePolicyService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  describe('business', () => {
    it('should be created', () => {
      expect(service).toBeTruthy();
    });

    it('should fetch all policies', () => {
      const expectedPolicies: InsurancePolicy[] = [
        {
          policyId: 1,
          policyNumber: '',
          policyType: '',
          premiumAmount: 0,
          startDate: new Date(),
          endDate: new Date(),
          isActive: false,
          customerId: 0
        }
      ];
      service.getAllPolicies().subscribe(policies => {
        expect(policies).toEqual(expectedPolicies);
      });
      const req = httpTestingController.expectOne('http://127.0.0.1:8081/insurancepolicy/api/policies');
      expect(req.request.method).toEqual('GET');
      req.flush(expectedPolicies);
    });

    it('should get policy by ID', () => {
      const testPolicy: InsurancePolicy = {
        policyId: 1,
        policyNumber: '',
        policyType: '',
        premiumAmount: 0,
        startDate: new Date(),
        endDate: new Date(),
        isActive: false,
        customerId: 0
      };
      service.getPolicyById(1).subscribe(policy => {
        expect(policy).toEqual(testPolicy);
      });
      const req = httpTestingController.expectOne('http://127.0.0.1:8081/insurancepolicy/api/policies/1');
      expect(req.request.method).toEqual('GET');
      req.flush(testPolicy);
    });

    it('should create a new policy', () => {
      const newPolicy: InsurancePolicy = {
        policyId: 1,
        policyNumber: '',
        policyType: '',
        premiumAmount: 0,
        startDate: new Date(),
        endDate: new Date(),
        isActive: false,
        customerId: 0
      };
      service.createPolicy(newPolicy).subscribe(policy => {
        expect(policy).toEqual(newPolicy);
      });
      const req = httpTestingController.expectOne('http://127.0.0.1:8081/insurancepolicy/api/policies');
      expect(req.request.method).toEqual('POST');
      req.flush(newPolicy);
    });

    it('should update an existing policy', () => {
      const updatedPolicy: InsurancePolicy = {
        policyId: 1,
        policyNumber: '',
        policyType: '',
        premiumAmount: 0,
        startDate: new Date(),
        endDate: new Date(),
        isActive: false,
        customerId: 0
      };
      service.updatePolicy(updatedPolicy.policyId, updatedPolicy).subscribe(policy => {
        expect(policy).toEqual(updatedPolicy);
      });
      const req = httpTestingController.expectOne(`http://127.0.0.1:8081/insurancepolicy/api/policies/${updatedPolicy.policyId}`);
      expect(req.request.method).toEqual('PUT');
      req.flush(updatedPolicy);
    });

    it('should delete a policy', () => {
      const policyId = 1;
      service.deletePolicy(policyId).subscribe(response => {
        expect(response).toBeUndefined();
      });
      const req = httpTestingController.expectOne(`http://127.0.0.1:8081/insurancepolicy/api/policies/${policyId}`);
      expect(req.request.method).toEqual('DELETE');
      req.flush({});
    });
  });
});
