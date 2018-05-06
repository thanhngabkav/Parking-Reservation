export interface TicketType {
    ticketTypeID?: number;
    stationVehicleTypeID?: number;
    price: number;
    holdingTime: string;
    name: string;
    serviceID: number;
    vehicleTypeName: string;
}

export interface VehicleType {
    id: number;
    name: string;
}

export interface Service {
    id: number;
    name: string;
}