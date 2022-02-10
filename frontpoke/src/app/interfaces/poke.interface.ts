export interface Poke{

    id:     number;
    nombre: string;
    peso:   null;
    altura: null;
    imagen: string;
    tipos:  null;


}
export interface PokeInfo {
    id?:     number;
    nombre?: string;
    peso?:   number;
    altura?: number;
    imagen?: string;
    tipos?:  string[];
}
export interface PokeCadena {
    id:     number;
    nombre: string;
    peso:   null;
    altura: null;
    imagen: string;
    tipos:  null;
}
export interface PokeStats {
    hp?:              number;
    attack?:          number;
    defense?:         number;
    special_attack?:  number;
    special_defense?: number;
    speed?:           number;
}


