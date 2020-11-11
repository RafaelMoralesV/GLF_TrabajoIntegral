package logicalTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import logical.Entidad;

public class TestEntidad {
	protected Entidad ent;
	@Before
	public void setUp() throws Exception {
		this.ent = new Entidad(10, 15, 'T', 1);
	}

	@Test
	public void testEntidadIntIntCharInt() {
		Entidad test = new Entidad(1, -2, 'X', 2);
		assertEquals(1, test.getPosicion_X());
		assertEquals(-2, test.getPosicion_Y());
		assertEquals('\0', test.getTipo());
		assertEquals(2, test.getIdentificador());
	}

	@Test
	public void testEntidad() {
		Entidad test = new Entidad();
		assertEquals(0, test.getPosicion_X());
		assertEquals(0, test.getPosicion_Y());
		assertEquals('\0', test.getTipo());
		assertEquals(-1, test.getIdentificador());
	}

	@Test
	public void testEntidadCharInt() {
		Entidad test = new Entidad('P', 3);
		assertEquals(0, test.getPosicion_X());
		assertEquals(0, test.getPosicion_Y());
		assertEquals('P', test.getTipo());
		assertEquals(3, test.getIdentificador());
	}

	@Test
	public void testGetPosicion_X() {
		assertEquals(10, this.ent.getPosicion_X());
	}

	@Test
	public void testSetPosicion_X() {
		this.ent.setPosicion_X(3);
		assertEquals(3, this.ent.getPosicion_X());
	}

	@Test
	public void testGetPosicion_Y() {
		assertEquals(15, this.ent.getPosicion_Y());
	}

	@Test
	public void testSetPosicion_Y() {
		this.ent.setPosicion_Y(3);
		assertEquals(3, this.ent.getPosicion_Y());
	}

	@Test
	public void testGetTipo() {
		assertEquals('\0', this.ent.getTipo());
	}

	@Test
	public void testSetTipo() {
		this.ent.setTipo('P');
		assertEquals('P', this.ent.getTipo());
		
		this.ent.setTipo('C');
		assertEquals('C', this.ent.getTipo());
		
		this.ent.setTipo('T');
		assertEquals('\0', this.ent.getTipo());
	}

	@Test
	public void testGetIdentificador() {
		assertEquals(1, this.ent.getIdentificador());
	}

	@Test
	public void testSetIdentificador() {
		this.ent.setIdentificador(10);
		assertEquals(10, this.ent.getIdentificador());
	}

	@Test
	public void testDistancia() {
		Entidad test = new Entidad(14, 18, 'T', 4);
		assertEquals(5, ent.distancia(test), 0);
	}

}
