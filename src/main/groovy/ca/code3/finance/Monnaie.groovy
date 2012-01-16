/*
 * Copyright (C) 2012  CODE3 Cooperative de solidarite
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
//Contribution Louis Martin
package ca.code3.finance

import java.math.RoundingMode
import java.text.NumberFormat

final class Monnaie implements Comparable<Monnaie> {
    
    static final ZERO = new Monnaie( 0 )
    
    final BigDecimal montant
    
    static Monnaie valueOf( montant ) {
        new Monnaie( montant )
    }

    Monnaie( BigDecimal montant ) {
        this.montant = montant.setScale( 2, RoundingMode.HALF_UP )
    }
    
    Monnaie( double montant ) {
        this( new BigDecimal( montant ) )
    }
    
    Monnaie( String montant ) {
        this( new BigDecimal( montant ) )
    }
    
    Monnaie plus( Monnaie monnaie ) {
        new Monnaie( montant + monnaie.montant )
    } 
    
    Monnaie minus( Monnaie monnaie ) {
        new Monnaie( montant - monnaie.montant )
    }
    
    Monnaie multiply( BigDecimal multiplicateur ) {
        new Monnaie( montant * multiplicateur )
    }
    
    Monnaie div( BigDecimal diviseur ) {
        new Monnaie( montant / diviseur )
    }
    
    String format( Locale locale ) {
        NumberFormat.getCurrencyInstance( locale ).format( montant )
    }
    
    String format() {
        format( Locale.getDefault() )
    }

    Object asType( Class classe ) {
        if ( classe == BigDecimal ) { return montant }
        if ( classe == String ) { return toString() }
        throw new ClassCastException()
    }
    
    String toString() {
        format()
    }
    
    int compareTo( monnaie ) {
        montant <=> monnaie.montant
    }

    int hashCode() { 
        montant.hashCode() 
    }

    boolean equals( objet ) { 
        objet instanceof Monnaie && montant == objet.montant 
    }
	
}

