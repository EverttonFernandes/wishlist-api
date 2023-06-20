const request = require('supertest')
const app = "http://localhost:8080"
import { v4 as uuidv4 } from "uuid"

describe("Specification with responsibility to create successful products", () => {
    it("should create a new product with success", async () => {
        const uuid = uuidv4()
        const newProduct = {
            name: "New Product Test".concat(uuid),
            price: "9.99"
        }

        const response = await request(app)
            .post('/products')
            .send(JSON.stringify(newProduct))
            .set('Content-Type', 'application/json')
            .expect(201)

        expect(response.body).toEqual(expect.anything())
    })
})
