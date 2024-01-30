import { Account } from "./account"

export interface Transaction {
    id: number
    sourceAccountId: Account
    recipientAccountId: Account
    amount: number
    transactionDate: string
}
