const request = require('supertest');
const app = "http://localhost:8080"
import { v4 as uuidv4 } from "uuid"

describe("Specification responsible for fetching previously created products", () => {
    it("should get success when get all products", async () => {
        const response = await request(app).get('/products').expect(200)

        expect(response.body).toEqual(expect.anything())
    })

    it("should get success when get product by id", async () => {
        const uuid = uuidv4()
        const newProduct = {
            name: "Playstation".concat(uuid),
            price: "8000"
        }

        const response = await request(app)
            .post('/products')
            .send(JSON.stringify(newProduct))
            .set('Content-Type', 'application/json')
            .expect(201)

        expect(response.body).toEqual(expect.anything())

        const selfLink = response.body._links.self.href
        const productId = selfLink.split('/').pop()

        const responseGET = await request(app).get('/products'.concat("/").concat(productId))

        expect(responseGET.status).toEqual(200)
        expect(responseGET.body).toEqual(expect.anything())
        expect(responseGET.body.name).toEqual(newProduct.name)
        expect(responseGET.body.price.toString()).toEqual(newProduct.price.toString())
    })
})