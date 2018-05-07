export interface Role {
    roleID: number;
    roleName: string;
}

export interface Owner {
    id?: string;
    fullName: string;
    email: string;
    phoneNumber: string;
    address: string;
    password: string;
    bank?: string;
    bankAccountNumber?: string;
    numStations: number;
    status: string;
    secretKey?: string;
}