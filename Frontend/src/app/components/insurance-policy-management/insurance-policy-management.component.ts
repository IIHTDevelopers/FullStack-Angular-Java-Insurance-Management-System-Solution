// TypeScript component
import { Component, OnInit } from '@angular/core';
import { InsurancePolicyService } from '../../services/insurance-policy.service';
import { InsurancePolicy } from '../../models/insurance-policy-management.model';

@Component({
  selector: 'app-insurance-policy',
  templateUrl: './insurance-policy-management.component.html',
  styleUrls: ['./insurance-policy-management.component.css']
})
export class InsurancePolicyComponent implements OnInit {
  policies: InsurancePolicy[] = [];
  selectedPolicy: InsurancePolicy = {
    policyId: 0,
    policyNumber: '',
    policyType: '',
    premiumAmount: 0,
    startDate: new Date(),
    endDate: new Date(),
    isActive: false,
    customerId: 0
  };

  constructor(private insurancePolicyService: InsurancePolicyService) { }

  ngOnInit(): void {
    this.loadPolicies();
  }

  loadPolicies(): void {
    this.insurancePolicyService.getAllPolicies().subscribe(
      policies => {
        this.policies = policies;
      },
      error => {
        console.error('Error loading policies:', error);
      }
    );
  }

  addPolicy(): void {
    this.insurancePolicyService.createPolicy(this.selectedPolicy).subscribe(
      () => {
        this.loadPolicies();
        this.selectedPolicy = this.createEmptyPolicy(); // Clear the form
      },
      error => {
        console.error('Error adding policy:', error);
      }
    );
  }

  showUpdateForm(id: number): void {
    // Retrieve policy details and display them in the form
    const selectedPolicy = this.policies.find(policy => policy.policyId === id);
    if (selectedPolicy) {
      this.selectedPolicy = { ...selectedPolicy };
    }
  }

  updatePolicyApi(): void {
    this.insurancePolicyService.updatePolicy(this.selectedPolicy.policyId, this.selectedPolicy).subscribe(
      () => {
        this.loadPolicies();
        this.selectedPolicy = this.createEmptyPolicy(); // Clear the form
      },
      error => {
        console.error('Error updating policy:', error);
      }
    );
  }

  deletePolicy(id: number): void {
    this.insurancePolicyService.deletePolicy(id).subscribe(
      () => {
        this.loadPolicies();
        this.selectedPolicy = this.createEmptyPolicy(); // Clear the form
      },
      error => {
        console.error('Error deleting policy:', error);
      }
    );
  }

  selectPolicy(policy: InsurancePolicy): void {
    this.selectedPolicy = { ...policy };
  }

  createEmptyPolicy(): InsurancePolicy {
    return {
      policyId: 0,
      policyNumber: '',
      policyType: '',
      premiumAmount: 0,
      startDate: new Date(),
      endDate: new Date(),
      isActive: false,
      customerId: 0
    };
  }
}
