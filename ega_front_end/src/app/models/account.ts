import { Client } from "./client"

export interface Account {
    accountNumber: number
    id: number
    accountType: string
    creationDate: string
    balance: number
    ownerName: Client
}
