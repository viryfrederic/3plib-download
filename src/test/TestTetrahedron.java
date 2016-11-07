/** Polygonal Planar Projection LIBrary (3plib) v0.1.0
 ** Copyright 2016 Frederic Viry
 ** author: Frederic Viry (Laboratoire Verimag, Grenoble, France)
 ** mail: ask3plib@gmail.com
 **
 ** This file is part of 3plib.
 **
 ** 3plib is free software: you can redistribute it and/or modify
 ** it under the terms of the GNU Lesser General Public License as published by
 ** the Free Software Foundation, either version 3 of the License, or
 ** at your option) any later version.
 ** 
 ** 3plib is distributed in the hope that it will be useful,
 ** but WITHOUT ANY WARRANTY; without even the implied warranty of
 ** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 ** GNU Lesser General Public License for more details.
 ** 
 ** You should have received a copy of the GNU Lesser General Public License
 ** along with 3plib. If not, see <http://www.gnu.org/licenses/>.
 **/

import fr.imag.ppplib.calc.ProjectionCalculator;
import fr.imag.ppplib.calc.sf.ConvexPolyhedronSupportFunction;
import fr.imag.ppplib.calc.ImplementationFactory;
import fr.imag.ppplib.proj.plg.Polygon;
import fr.imag.ppplib.proj.PolygonalProjector;
import fr.imag.ppplib.proj.UnionPolygonalProjector;
import fr.imag.ppplib.proj.ErrorType;
import fr.imag.ppplib.io.gui.PolygonsViewer;

import java.util.List;
import java.util.ArrayList;

/** A test class for a tetrahedron.
 **/

public class TestTetrahedron
{
  public static void main(String[] args)
  {
    /* Tetrahedron construction */
    double[] db = new double[] {0.0, 0.0, -1.0};
    double[] d1 = new double[] {-0.9659, 0, 0.2588};
    double[] d2 = new double[] {0.9659, 0, 0.2588};
    double[] d3 = new double[] {0.0, -0.9659, 0.2588};
    double[] d4 = new double[] {0.0, 0.9659, 0.2588};
    ConvexPolyhedronSupportFunction p = new ConvexPolyhedronSupportFunction();
    p.addLinearConstraint(db, 1.0);
    p.addLinearConstraint(d1, 1.0);
    p.addLinearConstraint(d2, 1.0);
    p.addLinearConstraint(d3, 1.0);
    p.addLinearConstraint(d4, 1.0);
 
    /* Creation of the list of convex polyhedra (here just one) */
    List <ConvexPolyhedronSupportFunction> chairParts = new ArrayList <ConvexPolyhedronSupportFunction> ();
    chairParts.add(p);
 
    /* Error choice */
    double err = 0.0001;
 
    /* Plane choice */
    double[] v2 = new double[] {Math.sqrt(1.0/4.0), Math.sqrt(1.0/4.0), Math.sqrt(1.0/2.0)};
    double[] v1 = new double[] {Math.sqrt(2)/2.0, -Math.sqrt(2)/2.0, 0.0};
    ProjectionCalculator pc = ImplementationFactory.getNewProjectionCalculator(v1, v2);
 
    /* Projections calculus */
    PolygonalProjector pp = new UnionPolygonalProjector(true); 
    pp.computeProjection(chairParts, pc, ErrorType.RELATIVE, err);
 
    /* Get the result */
    List <Polygon> outerPoly = new ArrayList <Polygon>();
    outerPoly.addAll(pp.getOuterPolygons());
 
    /* View the result */
    PolygonsViewer pv = new PolygonsViewer();
    pv.addPolygons(outerPoly);
  }
}
