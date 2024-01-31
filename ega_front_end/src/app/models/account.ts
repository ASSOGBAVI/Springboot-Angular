import { Client } from "./client"

export interface Account {
    accountNumber: string
    id: number
    accountType: string
    creationDate: string
    balance: number
    ownerName: Client
}
