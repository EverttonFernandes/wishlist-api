const request = require('supertest');
const app = "http://localhost:8080"
import { v4 as uuidv4 } from "uuid"

describe("Specification responsible for the successful deletion of a product", () => {
    it("should delete product by id with success", async () => {
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

        const selfLink = response.body._links.self.href
        const productId = selfLink.split('/').pop()

        const responseDELETE = await request(app).delete('/products'.concat("/").concat(productId))

        expect(responseDELETE.status).toEqual(200)
        expect(responseDELETE.body).toEqual({})
    })
})