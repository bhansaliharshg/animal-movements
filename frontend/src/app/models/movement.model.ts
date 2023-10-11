import { Farm } from "./farm.model";

export class Movement {
    id!: number;
    account!: string;
    movementReason!: string;
    species!: string;
    numberOfItemsMoved!: number;
    shipmentDate!: string;
    origin!: Farm;
    destination!: Farm;
}