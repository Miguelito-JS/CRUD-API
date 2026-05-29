const express = require("express");
const mysql = require("mysql2");
const cors = require("cors");
const axios = require("axios");

const app = express();
const PORT = 3000;

// Middlewares
app.use(cors());
app.use(express.json());
app.use(express.static("public"));

// Conexão com o MySQL
// ATENÇÃO: altere user e password conforme o seu MySQL.
const conexao = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "191221",
    database: "sistema_clientes"
});

conexao.connect((erro) => {
    if (erro) {
        console.error("Erro ao conectar no MySQL:", erro.message);
        console.log("Verifique se o MySQL está ligado, se o banco foi criado e se a senha está correta.");
        return;
    }

    console.log("Conectado ao banco MySQL!");
});

// Rota inicial da API
app.get("/api", (req, res) => {
    res.json({
        mensagem: "API de Clientes funcionando!",
        endpoints: [
            "GET /clientes",
            "POST /clientes",
            "PUT /clientes/:id",
            "DELETE /clientes/:id",
            "GET /consultar-cep/:cep"
        ]
    });
});

// Listar clientes
app.get("/clientes", (req, res) => {
    const sql = "SELECT * FROM clientes ORDER BY id DESC";

    conexao.query(sql, (erro, resultado) => {
        if (erro) {
            return res.status(500).json({ erro: "Erro ao listar clientes." });
        }

        res.json(resultado);
    });
});

// Cadastrar cliente
app.post("/clientes", (req, res) => {
    const { nome, email, telefone, cep, rua, bairro, cidade, estado } = req.body;

    if (!nome || !email) {
        return res.status(400).json({
            erro: "Nome e e-mail são obrigatórios."
        });
    }

    const sql = `
        INSERT INTO clientes 
        (nome, email, telefone, cep, rua, bairro, cidade, estado)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    `;

    const valores = [nome, email, telefone, cep, rua, bairro, cidade, estado];

    conexao.query(sql, valores, (erro, resultado) => {
        if (erro) {
            return res.status(500).json({ erro: "Erro ao cadastrar cliente." });
        }

        res.status(201).json({
            mensagem: "Cliente cadastrado com sucesso!",
            id: resultado.insertId
        });
    });
});

// Atualizar cliente
app.put("/clientes/:id", (req, res) => {
    const { id } = req.params;
    const { nome, email, telefone, cep, rua, bairro, cidade, estado } = req.body;

    const sql = `
        UPDATE clientes
        SET nome = ?, email = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?
        WHERE id = ?
    `;

    const valores = [nome, email, telefone, cep, rua, bairro, cidade, estado, id];

    conexao.query(sql, valores, (erro, resultado) => {
        if (erro) {
            return res.status(500).json({ erro: "Erro ao atualizar cliente." });
        }

        if (resultado.affectedRows === 0) {
            return res.status(404).json({ erro: "Cliente não encontrado." });
        }

        res.json({ mensagem: "Cliente atualizado com sucesso!" });
    });
});

// Excluir cliente
app.delete("/clientes/:id", (req, res) => {
    const { id } = req.params;

    const sql = "DELETE FROM clientes WHERE id = ?";

    conexao.query(sql, [id], (erro, resultado) => {
        if (erro) {
            return res.status(500).json({ erro: "Erro ao excluir cliente." });
        }

        if (resultado.affectedRows === 0) {
            return res.status(404).json({ erro: "Cliente não encontrado." });
        }

        res.json({ mensagem: "Cliente excluído com sucesso!" });
    });
});

// Consultar CEP usando API pública ViaCEP
app.get("/consultar-cep/:cep", async (req, res) => {
    try {
        let { cep } = req.params;

        cep = cep.replace(/\D/g, "");

        if (cep.length !== 8) {
            return res.status(400).json({
                erro: "CEP inválido. Digite apenas 8 números."
            });
        }

        const resposta = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);

        if (resposta.data.erro) {
            return res.status(404).json({
                erro: "CEP não encontrado."
            });
        }

        res.json({
            cep: resposta.data.cep,
            rua: resposta.data.logradouro,
            bairro: resposta.data.bairro,
            cidade: resposta.data.localidade,
            estado: resposta.data.uf
        });

    } catch (erro) {
        res.status(500).json({
            erro: "Erro ao consultar o CEP. Verifique sua conexão com a internet."
        });
    }
});

app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});
