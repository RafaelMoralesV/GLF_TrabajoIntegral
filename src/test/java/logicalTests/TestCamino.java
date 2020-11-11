package logicalTests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import logical.Camino;
import logical.Entidad;

public class TestCamino {

	protected Camino c;
	
	@Before
	public void setUp() throws Exception {
		c = new Camino(new Entidad(14, 13, 'C', 1));
		c.agregarEntidad(new Entidad(-2, 3, 'P', 2));
		c.agregarEntidad(new Entidad(5, -5, 'P', 3));
	}

	@Test
	public void testCaminoLinkedListOfEntidad() {
		LinkedList<Entidad> l = (LinkedList<Entidad>) c.getCamino();
		Camino test = new Camino(l);
		
		assertEquals(c.getCamino(), test.getCamino());
		
		Entidad e = l.poll();
		l.offer(e);
		test = new Camino(l);
		assertNotEquals(c.getCamino(), test.getCamino());
	}

	@Test
	public void testAgregaryQuitar() {
		int size = c.getCamino().size();
		c.agregarEntidad(new Entidad());
		
		assertEquals(size + 1, c.getCamino().size());
		
		c.eliminarEntidad(-1);
		
		assertEquals(size, c.getCamino().size());
	}
	
	@Test
	public void testGetCamino() {
		LinkedList<Entidad> l = (LinkedList<Entidad>) c.getCamino();
		
		l.poll();
		
		int i = 2;
		for(Entidad e : l) {
			assertEquals(i, e.getIdentificador());
			i++;
		}
	}

	@Test
	public void testDistanciaTotal() {
		assertEquals(36.56917, c.distanciaTotal(), 0.00001);
	}

}
