import { Producto } from "./producto";

export interface Carrito
{
    id: number;
    usuarioId: number;
    productos: Producto[];
    total: number;

}