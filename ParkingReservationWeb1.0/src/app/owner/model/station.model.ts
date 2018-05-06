export interface Station {
    applicationID?: string;
    name: string;
    address: string;
    createdDate: number;
    status: string;
    star?: number;
    openTime: string;
    closeTime: string;
    imageLink?: string;
    totalSlots?: number;
    usedSlots?: number;
    holdingSlots?: number;
    parkingMapLink?: string;
    coordinate: string;
    ownerID?: string;
}

export interface Report {
    numCheckedTickets: number;
    numExpiredTickets: number;
}



