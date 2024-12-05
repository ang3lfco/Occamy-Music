use bibliotecaMusical7

db.usuarios.find({});
db.artistas.find({});
db.albumes.find({});

db.usuarios.insertOne({
    _id: ObjectId(),
    nombre: "Francisco",
    correo: "francisco@example.com",
    contraseña: "123",
    imagenPath: "",
    favoritos: {
        artistas: [
            ObjectId("674aa2cf96f4525593072f8a")
        ],
        albumes: [
            ObjectId("674aa2ea96f4525593072f8b")
        ],
        canciones: [
            {
                titulo: "Worldwide",
                albumId: ObjectId("674aa2ea96f4525593072f8b")
            }
        ]
    },
    bloqueados: {
	generos: [
            "Nu Metal",
            "Reggaeton"
        ]
    }
});

db.artistas.insertOne({
        _id: ObjectId(),
        nombre: "Big Time Rush",
        tipo: "banda",
        imagenPath: "",
        genero: "Pop",
        integrantes: [
            { nombre: "Kendall", apellido: "Schmidt", rol: "Vocalista", fechaIngreso: ISODate("2009-01-01"), estadoActivo: true },
            { nombre: "James", apellido: "Maslow", rol: "Vocalista", fechaIngreso: ISODate("2009-01-01"), estadoActivo: true },
            { nombre: "Carlos", apellido: "Pena Jr.", rol: "Vocalista", fechaIngreso: ISODate("2009-01-01"), estadoActivo: true },
            { nombre: "Logan", apellido: "Henderson", rol: "Vocalista", fechaIngreso: ISODate("2009-01-01"), estadoActivo: true }
        ]
});

db.albumes.insertOne({
    _id: ObjectId(),
    nombre: "BTR",
    fechaLanzamiento: ISODate("2010-10-11"),
    genero: "Pop",
    portadaPath: "",
    canciones: [
        "Boyfriend",
        "Til I Forget About You",
        "Worldwide"
    ],
    artista: "Big Time Rush"
});
