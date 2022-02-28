import { Utente } from "./Utente";

export class Ruolo{
    public idRuolo !:number;
    public nome!:string;
    public admin:boolean=false;
    public ruoli!:Array<Utente>;
}