from locust import HttpUser, task, between


class QuickstartUser(HttpUser):
    wait_time = between(1, 2)

    def on_start(self):
        self.client.get("/function/system/functions")

    @task(7)
    def invoke_with_b(self):
        self.client.get("/function/a?next=b")

    @task(3)
    def invoke_with_a(self):
        self.client.get("/function/a?next=c")
