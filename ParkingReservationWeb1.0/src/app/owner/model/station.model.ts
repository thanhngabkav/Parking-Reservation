export interface Service {
    serviceName: string;
    serviceID: number;
}

export interface StationVehicleType {
    id: number;
    vehicleTypeId: number;
    stationID: number;
    totalSlots: number;
    usedSlots: number;
    holdingSlots: number;
}

export interface Station {
    applicationID: string;
    name: string;
    address: string;
    createdDate: number;
    status: string;
    star: number;
    openTime: string;
    closeTime: string;
    imageLink: string;
    totalSlots: number;
    usedSlots: number;
    holdingSlots: number;
    parkingMapLink: string;
    coordinate: string;
    ownerID: string;
    services: Service[];
    stationVehicleTypes: StationVehicleType[];
    id?: number;
}

export interface Service {
    serviceName: string;
    serviceID: number;
}

export interface Report {
    numCheckedTickets: number;
    numExpiredTickets: number;
}



