-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2024 a las 02:23:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbalmacen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `idcategoria` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `condicion` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idcategoria`, `nombre`, `descripcion`, `condicion`) VALUES
(1, 'Azúcar', 'Azúcar Rubia', b'1'),
(2, 'Arroz', 'Arroz Alejandrina', b'1'),
(3, 'Aceite', 'Aceite palmerola', b'1'),
(4, 'Fideos', 'Fideos Alianza', b'1'),
(5, 'Menestras', 'Menestra tipo lenteja', b'1'),
(6, 'Leche', 'Leche Gloria', b'1'),
(7, 'Huevo', 'Huevo fresco', b'1'),
(8, 'Atún', 'Atún AA', b'1'),
(9, 'Filete', 'Filete Gloria', b'1'),
(10, 'Detergente', 'Detergente Marsella', b'1'),
(11, 'Jabón', 'Jabón Bolívar Azul', b'1'),
(12, 'Pasta Dental', 'Pasta Dental Colgate', b'1'),
(13, 'Verduras', 'Verduras frescas', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idcliente` int(11) NOT NULL,
  `nombre_cliente` varchar(400) NOT NULL,
  `tipo_documento` varchar(20) NOT NULL,
  `documento` varchar(20) DEFAULT NULL,
  `tipo_cliente` varchar(50) NOT NULL,
  `telefono` int(20) NOT NULL,
  `direccion` text DEFAULT NULL,
  `condicion` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idcliente`, `nombre_cliente`, `tipo_documento`, `documento`, `tipo_cliente`, `telefono`, `direccion`, `condicion`) VALUES
(1, 'Lalo Ramos', 'DNI', '65948217', 'Cliente Frecuente', 985647231, 'Jr. Aguaytia', b'1'),
(2, 'León Ruiz', '12364587', '12364587', 'Cliente', 956487512, 'Jr. Las Palmeras', b'1'),
(3, 'LEONCITO S.A.C', 'RUC', '2068954714', 'Proveedor', 985647231, 'Av. Centenario', b'1'),
(4, 'Joel Ramirez', 'RUC', '65987412', 'Cliente', 956956624, 'La Túpac', b'1'),
(5, 'Ingrid Witting', 'DNI', '65489712', 'Cliente Nuevo', 956326985, 'Av. Miraflores', b'1'),
(6, 'RANITA S.A.C', 'RUC', '25465987812', 'Proveedor', 956365258, 'Av. Centenario', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idcompra` int(11) NOT NULL,
  `codigo_compra` varchar(15) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `serie_comprobante` varchar(100) NOT NULL,
  `fecha_compra` datetime NOT NULL,
  `IGV` decimal(2,2) NOT NULL,
  `total` decimal(10,2) DEFAULT 0.00,
  `condicion` enum('Aceptado','Rechazado') NOT NULL,
  `cantidad_producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `compras`
--
DELIMITER $$
CREATE TRIGGER `boleta_factura` BEFORE INSERT ON `compras` FOR EACH ROW begin 
declare next int;
if new.tipo_comprobante = 'Boleta' then
        set next = (select ifnull(max(convert(substring(codigo_compra, 4),signed integer)), 0)from compras where codigo_compra like 'BOL%') + 1;
        set new.codigo_compra = concat('BOL', LPAD(next, 5, '0'));
    elseif new.tipo_comprobante = 'Factura' then
        set next = (select ifnull(max(convert(substring(codigo_compra, 4),signed integer)), 0)from compras where codigo_compra like 'FAC%') + 1;
        set new.codigo_compra = concat('FAC',LPAD(next, 5, '0'));
    else
        set new.codigo_compra = 'UNK00001';
    end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `validar_compra` BEFORE INSERT ON `compras` FOR EACH ROW begin
    declare v_precio_producto decimal(10,2);
    declare v_subtotal decimal(10,2);
    declare v_total decimal(10,2);
    select precio_total into v_precio_producto
    from productos 
    where producto_id = new.producto_id;
    set v_subtotal = new.cantidad_producto * v_precio_producto;
    set v_total = v_subtotal + (v_subtotal * new.IGV);
    set new.total = v_total;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `consulta_clientes`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `consulta_clientes` (
`idcliente` int(11)
,`nombre_cliente` varchar(400)
,`tipo_documento` varchar(20)
,`documento` varchar(20)
,`tipo_cliente` varchar(50)
,`telefono` int(20)
,`direccion` text
,`condicion` bit(1)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `consulta_productos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `consulta_productos` (
`Categorias` varchar(100)
,`Productos` varchar(403)
,`Cantidad` int(10)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idproducto` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `nombre_producto` varchar(300) NOT NULL,
  `descripcion_producto` varchar(300) NOT NULL,
  `imagen_producto` varchar(100) NOT NULL,
  `codigo_producto` varchar(10) NOT NULL,
  `marca_producto` varchar(100) NOT NULL,
  `cantidad_producto` int(10) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `precio_compra` decimal(10,2) NOT NULL,
  `condicion` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idproducto`, `categoria_id`, `nombre_producto`, `descripcion_producto`, `imagen_producto`, `codigo_producto`, `marca_producto`, `cantidad_producto`, `fecha_vencimiento`, `precio_compra`, `condicion`) VALUES
(1, 2, 'Arroz regional', 'Arroz regional graneado', 'Yeji_3.png', 'CD00001', 'Alejandrina', 15, '2024-12-20', 150.00, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `idrol` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `condicion` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idrol`, `nombre`, `descripcion`, `condicion`) VALUES
(1, 'Administrador', 'Administrador del Sistema(Product Owver)', b'1'),
(2, 'Asistente', 'Mano derecha del Administrador', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL,
  `idrol` int(11) DEFAULT NULL,
  `nombre` varchar(200) NOT NULL,
  `tipo_documento` varchar(20) NOT NULL,
  `documento` varchar(20) NOT NULL,
  `direccion` text DEFAULT NULL,
  `telefono` int(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `clave` varchar(128) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `condicion` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `idrol`, `nombre`, `tipo_documento`, `documento`, `direccion`, `telefono`, `email`, `clave`, `imagen`, `condicion`) VALUES
(1, 1, 'Jonh Perez', 'DNI', '65983245', 'Av. Centenario', 956987456, 'admin@gmail.com', '65bf6501d80f07d4cfce6079a5d2fe403be5b4d9f581ef1d4b8aed66168ed1a0', 'Yeji_1.jpg', 1),
(2, 2, 'Joel Ramírez', 'DNI', '65987845', 'Jr. Aguaytia', 936528541, 'asistente@gmail.com', '480f2649f990ed8a785841bacdab7395f7d4930ccdc94fd34ca5e19e7c66f3fc', 'Yeji_2.jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idventa` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `numero_comprobante` varchar(100) NOT NULL,
  `serie_comprobante` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_venta` decimal(10,2) NOT NULL,
  `IGV` decimal(2,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha_venta` datetime NOT NULL,
  `condicion` enum('Aceptado','Rechazado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `ventas`
--
DELIMITER $$
CREATE TRIGGER `fecha_actualventa` BEFORE INSERT ON `ventas` FOR EACH ROW begin 
set new.fecha_venta = now();
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `validar_venta` BEFORE INSERT ON `ventas` FOR EACH ROW begin
    declare v_precio_producto decimal(10,2);
    declare v_subtotal decimal(10,2);
    declare v_total decimal(10,2);
    declare v_count int default 0;
    declare v_numero_comprobante varchar(100);
    declare v_serie_comprobante varchar(100);
    select precio_total into v_precio_producto 
    from productos 
    where producto_id = new.producto_id;
    if v_precio_producto is null or v_precio_producto <= 0 then
        signal sqlstate '45000' set message_text = 'Precio del producto no válido o no encontrado.';
    end if;
    if new.cantidad is null or new.cantidad <= 0 then
        signal sqlstate '45000' set message_text = 'La cantidad de productos debe ser mayor que cero.';
    end if;
    set new.precio_venta = v_precio_producto;
    set v_subtotal = new.cantidad * v_precio_producto;
    set v_total = v_subtotal + (v_subtotal * new.IGV);
    if v_total <= 0 then
        signal sqlstate '45000' set message_text = 'El total debe ser mayor que cero.';
    end if;
    set new.total = v_total;
    if new.tipo_comprobante = 'Boleta' then
        select count(*) + 1 into v_count from ventas where tipo_comprobante = 'Boleta';
        set v_numero_comprobante = CONCAT('BOL', LPAD(v_count, 5, '0'));
        set v_serie_comprobante = LPAD(v_count, 5, '0');
    elseif new.tipo_comprobante = 'Factura' then
        select count(*) + 1 into v_count from ventas where tipo_comprobante = 'Factura';
        set v_numero_comprobante = CONCAT('FAC', LPAD(v_count, 5, '0'));
        set v_serie_comprobante = LPAD(v_count, 5, '0');
    else
        signal sqlstate '45000' set message_text = 'Tipo de comprobante no válido.';
    end if;
    set new.numero_comprobante = v_numero_comprobante;
    set new.serie_comprobante = v_serie_comprobante;
    update productos
    set cantidad_producto = cantidad_producto - new.cantidad
    where producto_id = new.producto_id;
    if (select cantidad_producto from productos where producto_id = new.producto_id) < 0 then
        signal sqlstate '45000' set message_text = 'Stock insuficiente para realizar la venta.';
    end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura para la vista `consulta_clientes`
--
DROP TABLE IF EXISTS `consulta_clientes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `consulta_clientes`  AS SELECT `clientes`.`idcliente` AS `idcliente`, `clientes`.`nombre_cliente` AS `nombre_cliente`, `clientes`.`tipo_documento` AS `tipo_documento`, `clientes`.`documento` AS `documento`, `clientes`.`tipo_cliente` AS `tipo_cliente`, `clientes`.`telefono` AS `telefono`, `clientes`.`direccion` AS `direccion`, `clientes`.`condicion` AS `condicion` FROM `clientes` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `consulta_productos`
--
DROP TABLE IF EXISTS `consulta_productos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `consulta_productos`  AS SELECT `c`.`nombre` AS `Categorias`, concat(`p`.`nombre_producto`,' - ',`p`.`marca_producto`) AS `Productos`, `p`.`cantidad_producto` AS `Cantidad` FROM (`productos` `p` join `categorias` `c` on(`p`.`categoria_id` = `c`.`idcategoria`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idcategoria`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idcompra`),
  ADD KEY `producto_id` (`producto_id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idproducto`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `usuarios_ibfk_1` (`idrol`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idventa`),
  ADD KEY `producto_id` (`producto_id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `idcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idcompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idventa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`idproducto`),
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`idcliente`),
  ADD CONSTRAINT `compras_ibfk_3` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`idcategoria`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `idrol` FOREIGN KEY (`idrol`) REFERENCES `roles` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
