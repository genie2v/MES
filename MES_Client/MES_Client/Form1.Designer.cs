namespace MES_Client
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다.
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마십시오.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnCreateLot = new System.Windows.Forms.Button();
            this.btnViewLotList = new System.Windows.Forms.Button();
            this.btnViewLotHis = new System.Windows.Forms.Button();
            this.btnUndoLot = new System.Windows.Forms.Button();
            this.btnMoveIn = new System.Windows.Forms.Button();
            this.button6 = new System.Windows.Forms.Button();
            this.btnMoveOut = new System.Windows.Forms.Button();
            this.button8 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnCreateLot
            // 
            this.btnCreateLot.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnCreateLot.Location = new System.Drawing.Point(12, 20);
            this.btnCreateLot.Name = "btnCreateLot";
            this.btnCreateLot.Size = new System.Drawing.Size(206, 55);
            this.btnCreateLot.TabIndex = 0;
            this.btnCreateLot.Text = "Create Lot";
            this.btnCreateLot.UseVisualStyleBackColor = true;
            this.btnCreateLot.Click += new System.EventHandler(this.btnCreateLot_Click);
            // 
            // btnViewLotList
            // 
            this.btnViewLotList.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnViewLotList.Location = new System.Drawing.Point(227, 20);
            this.btnViewLotList.Name = "btnViewLotList";
            this.btnViewLotList.Size = new System.Drawing.Size(206, 55);
            this.btnViewLotList.TabIndex = 1;
            this.btnViewLotList.Text = "View Lot List";
            this.btnViewLotList.UseVisualStyleBackColor = true;
            this.btnViewLotList.Click += new System.EventHandler(this.btnViewLotList_Click);
            // 
            // btnViewLotHis
            // 
            this.btnViewLotHis.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnViewLotHis.Location = new System.Drawing.Point(12, 81);
            this.btnViewLotHis.Name = "btnViewLotHis";
            this.btnViewLotHis.Size = new System.Drawing.Size(206, 55);
            this.btnViewLotHis.TabIndex = 2;
            this.btnViewLotHis.Text = "View Lot History";
            this.btnViewLotHis.UseVisualStyleBackColor = true;
            this.btnViewLotHis.Click += new System.EventHandler(this.btnViewLotHis_Click);
            // 
            // btnUndoLot
            // 
            this.btnUndoLot.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnUndoLot.Location = new System.Drawing.Point(227, 81);
            this.btnUndoLot.Name = "btnUndoLot";
            this.btnUndoLot.Size = new System.Drawing.Size(206, 55);
            this.btnUndoLot.TabIndex = 3;
            this.btnUndoLot.Text = "Undo Lot";
            this.btnUndoLot.UseVisualStyleBackColor = true;
            this.btnUndoLot.Click += new System.EventHandler(this.btnUndoLot_Click);
            // 
            // btnMoveIn
            // 
            this.btnMoveIn.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnMoveIn.Location = new System.Drawing.Point(12, 142);
            this.btnMoveIn.Name = "btnMoveIn";
            this.btnMoveIn.Size = new System.Drawing.Size(206, 55);
            this.btnMoveIn.TabIndex = 4;
            this.btnMoveIn.Text = "Move In";
            this.btnMoveIn.UseVisualStyleBackColor = true;
            this.btnMoveIn.Click += new System.EventHandler(this.btnMoveIn_Click);
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(227, 142);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(206, 55);
            this.button6.TabIndex = 5;
            this.button6.Text = "button6";
            this.button6.UseVisualStyleBackColor = true;
            // 
            // btnMoveOut
            // 
            this.btnMoveOut.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnMoveOut.Location = new System.Drawing.Point(12, 203);
            this.btnMoveOut.Name = "btnMoveOut";
            this.btnMoveOut.Size = new System.Drawing.Size(206, 55);
            this.btnMoveOut.TabIndex = 6;
            this.btnMoveOut.Text = "Move Out";
            this.btnMoveOut.UseVisualStyleBackColor = true;
            this.btnMoveOut.Click += new System.EventHandler(this.btnMoveOut_Click);
            // 
            // button8
            // 
            this.button8.Location = new System.Drawing.Point(227, 203);
            this.button8.Name = "button8";
            this.button8.Size = new System.Drawing.Size(206, 55);
            this.button8.TabIndex = 7;
            this.button8.Text = "button8";
            this.button8.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(445, 511);
            this.Controls.Add(this.button8);
            this.Controls.Add(this.btnMoveOut);
            this.Controls.Add(this.button6);
            this.Controls.Add(this.btnMoveIn);
            this.Controls.Add(this.btnUndoLot);
            this.Controls.Add(this.btnViewLotHis);
            this.Controls.Add(this.btnViewLotList);
            this.Controls.Add(this.btnCreateLot);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnCreateLot;
        private System.Windows.Forms.Button btnViewLotList;
        private System.Windows.Forms.Button btnViewLotHis;
        private System.Windows.Forms.Button btnUndoLot;
        private System.Windows.Forms.Button btnMoveIn;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button btnMoveOut;
        private System.Windows.Forms.Button button8;
    }
}

