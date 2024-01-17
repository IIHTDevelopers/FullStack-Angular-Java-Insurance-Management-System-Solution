import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InsurancePolicy } from '../models/insurance-policy-management.model';

@Injectable({
  providedIn: 'root'
})
export class InsurancePolicyService {
  private apiUrl = 'http://127.0.0.1:8081/insurancepolicy/api/policies';

  constructor(private http: HttpClient) { }

  getAllPolicies(): Observable<InsurancePolicy[]> {
    return this.http.get<InsurancePolicy[]>(`${this.apiUrl}`);
  }

  getPolicyById(id: number): Observable<InsurancePolicy> {
    return this.http.get<InsurancePolicy>(`${this.apiUrl}/${id}`);
  }

  createPolicy(policy: InsurancePolicy): Observable<InsurancePolicy> {
    return this.http.post<InsurancePolicy>(`${this.apiUrl}`, policy);
  }

  updatePolicy(id: number, policy: InsurancePolicy): Observable<InsurancePolicy> {
    return this.http.put<InsurancePolicy>(`${this.apiUrl}/${id}`, policy);
  }

  deletePolicy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
