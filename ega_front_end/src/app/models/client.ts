import { Account } from "./account"

export interface Client {
    id: number
    clientLastName: string
    clientFirstName: string
    birthDate: string
    sexe: string
    address: string
    phone: string
    nationality: string
    creationDate: string
    accounts: Account[]
}
