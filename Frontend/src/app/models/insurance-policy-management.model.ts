export interface InsurancePolicy {
    policyId: number;
    policyNumber: string;
    policyType: string;
    premiumAmount: number;
    startDate: Date;
    endDate: Date;
    isActive: boolean;
    customerId: number;
}
